package com.yadlings.restfull.v1.Controllers;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Domain.Vote;
import com.yadlings.restfull.Service.PollService;
import com.yadlings.restfull.Service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController(value = "V1 Votes")
@RequestMapping("/v1/poll/{pollId}/vote")
@Api(value = "Votes",description = "Votes Api")
public class VoteController {
    @Autowired
    private VoteService voteService;
    @GetMapping
    public ResponseEntity<?> getVotes(@PathVariable("pollId") String pollId){
        return voteService.getVotes(pollId);
    }
    @PostMapping
    public ResponseEntity<?> saveVote(@PathVariable("pollId") String pollId,@Valid @RequestBody Vote vote){
        return voteService.saveVote(pollId,vote);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVote(@PathVariable("id") String id){
        return voteService.getVote(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVote(@PathVariable("id") String id){
        return voteService.deleteVote(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePoll(@PathVariable("id") String id,@Valid @RequestBody Vote vote){
        return voteService.updatePoll(id,vote);
    }

}
