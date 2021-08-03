package com.yadlings.restfull.Service;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Domain.Vote;
import com.yadlings.restfull.Exception.ResourceException;
import com.yadlings.restfull.Repository.PollRepository;
import com.yadlings.restfull.Repository.VoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PollRepository pollRepository;
    public ResponseEntity<?> getVotes(String pollId) {
        List<Vote> all = voteRepository.findByPollId(pollId);
        if(all.size() < 0) throw new ResourceException.NotFound("No Poll Exist by Id "+pollId);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    public ResponseEntity<?> saveVote(String pollId,Vote vote) {
        pollRepository.findById(pollId)
                        .map(poll -> {
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
}
