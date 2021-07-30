package com.yadlings.restfull.Controllers;

import com.yadlings.restfull.Service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

@RestController
@RequestMapping("/poll")
public class VoteController {
    @Autowired
    private PollService pollService;
    @GetMapping
    public ResponseEntity<?> getPoll(){
        return pollService.getPoll();
    }
    @PostMapping
    @PutMapping
    @DeleteMapping
    @GetMapping
    @PostMapping
    @DeleteMapping

}
