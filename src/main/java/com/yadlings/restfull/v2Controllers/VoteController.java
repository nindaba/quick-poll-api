package com.yadlings.restfull.v2Controllers;

import com.yadlings.restfull.Domain.Vote;
import com.yadlings.restfull.Service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController(value = "V2 Votes")
@RequestMapping("/v2/poll/{pollId}/votes")
@Api(value = "Votes",description = "Votes Api")
public class VoteController {
    @Autowired
    private VoteService voteService;
    @GetMapping
    public ResponseEntity<?> getVotes(@PathVariable("pollId") String pollId){
        return voteService.getVotes(pollId);
    }
    @PostMapping
    public ResponseEntity<?> saveVote(@PathVariable("pollId") String pollId,@Valid @RequestBody Vote vote,@RequestParam String userid){
        return voteService.saveVote(pollId,vote,userid);
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
