package com.yadlings.restfull.Service;

import com.yadlings.restfull.Repository.PollRepository;
import com.yadlings.restfull.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepository;

}
