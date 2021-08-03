package com.yadlings.restfull.v3.Controllers;

import com.yadlings.restfull.Domain.Vote;
import com.yadlings.restfull.Service.VoteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController(value = "V3 Votes")
@RequestMapping("/v3/poll/{pollId}/votes")
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
