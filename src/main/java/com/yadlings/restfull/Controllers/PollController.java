package com.yadlings.restfull.Controllers;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poll")
public class PollController {
    @Autowired
    private PollService pollService;
    @GetMapping
    public ResponseEntity<?> getPoll(){
        return pollService.getPoll();
    }
    @PostMapping
    public ResponseEntity<?> savePoll(@RequestBody Poll poll){
        return pollService.savePoll(poll);
    }
    @PutMapping
    @DeleteMapping
    @GetMapping
    @PostMapping
    @DeleteMapping

}
