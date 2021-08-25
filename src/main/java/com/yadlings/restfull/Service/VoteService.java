package com.yadlings.restfull.Service;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Domain.UserVoted;
import com.yadlings.restfull.Domain.Vote;
import com.yadlings.restfull.Exception.ResourceException;
import com.yadlings.restfull.Repository.PollRepository;
import com.yadlings.restfull.Repository.UserVotedRepository;
import com.yadlings.restfull.Repository.VoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteCount voteCount;
    @Autowired
    private UserVotedRepository userVotedRepository;
    public ResponseEntity<?> getVotes(String pollId) {
        return voteCount.getResults(pollId);
    }

    public ResponseEntity<?> saveVote(String pollId,Vote vote,String userId) {
//        if(userVotedRepository.findById(userId).isPresent()) throw new ResourceException.AlreadyExist("You have already voted");
        userVotedRepository
                .findById(userId)
                .map(userVoted -> {
                    if(userVoted.getPolls().contains(pollId)) throw new ResourceException.AlreadyExist("You have already voted");
                    userVoted.getPolls().add(pollId);
                    return userVotedRepository.save(userVoted);
                })
                .orElseGet(()->userVotedRepository.save(new UserVoted(userId, Arrays.asList(pollId))));
        pollRepository.findById(pollId)
                        .map(poll -> {
                            if(!poll.getOptions().contains(vote.getOption())) throw new ResourceException.NotFound("Option "+vote.getOption()+" does not exist");
                            vote.setPollId(pollId);
                            return poll;}
                        ).orElseThrow(()-> new ResourceException.NotFound("No Poll Exist by Id "+pollId));
        Vote save = voteRepository.save(vote);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/poll/{pollId}/votes/{id}")
                .buildAndExpand("pollId",save.getPollId(),"id",save.getId())
                .toUri()
        );
        voteCount.count(save);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getVote(String id) {
        return voteRepository.findById(id)
                .map(vote -> new ResponseEntity<>(vote, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    public ResponseEntity<?> deleteVote(String id) {
        voteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updatePoll(String id, Vote vote) {
        return voteRepository.findById(id)
                .map(existingVote-> {
                    vote.setId(existingVote.getId());
                    voteRepository.deleteById(id);
                    voteRepository.save(vote);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> results() {
        throw new ResourceException.NotFound("Not yet Configured");
    }

    public ResponseEntity<?> votedResults(String userId) {
        List voted = userVotedRepository
                .findById(userId)
                .map(userVoted -> userVoted.getPolls())
                .orElse(new ArrayList<>());
        List<Vote> all = voteRepository.findAll();
        if(all.size()==0) throw new ResourceException.NotFound("Not Voted Yet");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
