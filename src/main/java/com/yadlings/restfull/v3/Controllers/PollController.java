package com.yadlings.restfull.v3.Controllers;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Exception.RestException;
import com.yadlings.restfull.Service.PollService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("V3 Poll")
@RequestMapping({"/v3/poll","/oauth2/v3/poll"})
@Api(value = "Poll",description = "Poll API")
public class PollController {
    @Autowired
    private PollService pollService;
    @GetMapping
    @ApiOperation(value = "Retrieve all polls",response = List.class)
    public ResponseEntity<List<Poll>> getPoll(){
        return pollService.getPoll();
    }
    @PostMapping
    @ApiOperation(value = "Create a new Poll",response = Void.class,notes = "The newly Created Poll's Id will be sent in response Headers in the Location")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message ="Poll Created Successfully",response = Void.class),
            @ApiResponse(code = 409,message = "BAD_REQUEST",response = RestException.class)
    })
    public ResponseEntity<?> savePoll(@Valid @RequestBody Poll poll){
        return pollService.savePoll(poll);
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve a poll associated with a poll id",response = Poll.class)
    public ResponseEntity<?> getPoll(@PathVariable("id") String id){
        return pollService.getPoll(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deletePoll(@PathVariable("id") String id){
        return pollService.deletePoll(id);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updatePoll(@PathVariable("id") String id,@Valid @RequestBody Poll poll){
        return pollService.updatePoll(id,poll);
    }

}
