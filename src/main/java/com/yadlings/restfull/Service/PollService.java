package com.yadlings.restfull.Service;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {
    @Autowired
    PollRepository pollRepository;

    public ResponseEntity<?> getPoll() {
        List<Poll> all = pollRepository.findAll();
        return all.size() > 0
                ? new ResponseEntity<List>(all, HttpStatus.NO_CONTENT)
                : new ResponseEntity<List>(all, HttpStatus.OK);
    }

    public ResponseEntity<?> savePoll(Poll poll) {
        pollRepository.findBy
        pollRepository.save(poll);
    }
}
