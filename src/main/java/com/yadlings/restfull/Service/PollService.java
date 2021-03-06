package com.yadlings.restfull.Service;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Exception.ResourceException;
import com.yadlings.restfull.Repository.PollRepository;
import com.yadlings.restfull.Repository.UserVotedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollService {
    @Autowired
    PollRepository pollRepository;
    @Autowired
    UserVotedRepository userVotedRepository;

    public ResponseEntity<List<Poll>> getPolls(String userId) {
        List voted = userVotedRepository
                .findById(userId)
                .map(userVoted -> userVoted.getPolls())
                .orElse(new ArrayList<>());

        List<Poll> all = pollRepository
                .findAll()
                .stream()
                .dropWhile(poll -> voted.contains(poll.getId()))
                .collect(Collectors.toList());
        if (all.size() == 0) throw new ResourceException.NotFound("No Poll Available In Database");
        return new ResponseEntity<>(all, HttpStatus.OK);


    }

    public ResponseEntity<?> savePoll(Poll poll) {
        pollRepository.findByQuestion(poll.getQuestion()).ifPresent(existingPoll->{
            throw new ResourceException.AlreadyExist("The Question Already Exist");
        });
        Poll save = pollRepository.save(poll);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("v1/poll/{id}")
                .buildAndExpand(save.getId())
                .toUri()
        );
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPoll(String id) {
        return pollRepository.findById(id)
                .map(poll -> new ResponseEntity<>(poll, HttpStatus.OK))
                .orElseThrow(()->new ResourceException.NotFound("Poll Does Not Exist"));
    }
    public ResponseEntity<?> deletePoll(String id) {
        pollRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> updatePoll(String id, Poll poll) {
        return pollRepository.findById(id)
                .map(existingPoll-> {
                    poll.setId(existingPoll.getId());
                    pollRepository.deleteById(id);
                    pollRepository.save(poll);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<?> getPages(Pageable pageable){
        Page<Poll> all = pollRepository.findAll(pageable);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}
