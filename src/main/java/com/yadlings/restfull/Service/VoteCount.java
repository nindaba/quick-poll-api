package com.yadlings.restfull.Service;

import com.yadlings.restfull.Domain.PollResults;
import com.yadlings.restfull.Domain.Vote;
import com.yadlings.restfull.Exception.ResourceException;
import com.yadlings.restfull.Repository.PollResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VoteCount {
    @Autowired
    private PollResultsRepository repository;

    public void count(Vote vote){
        repository.findById(vote.getPollId())
                .map(pollResults -> update(pollResults,vote))
                .orElseGet(()-> newPollResults(vote));
    }
    private PollResults update(PollResults pollResults,Vote vote){
        Map<String, Integer> optionVotes = pollResults.getOptionVotes();
        String key = vote.getOption().getValue();
        Integer voteCount = optionVotes.get(key)+1;
        pollResults.getOptionVotes().put(key,voteCount);
        return repository.save(pollResults);
    }
    private PollResults newPollResults(Vote vote){
        PollResults pollResults = new PollResults();
        pollResults.setId(vote.getPollId());
        pollResults.getOptionVotes().put(vote.getOption().getValue(),1);
        pollResults.setLastVote(vote.getTimestamp());
        return repository.save(pollResults);
    }
    public ResponseEntity<List> getResults(){
        List<PollResults> all = repository.findAll();
        if(all.size()>0) return new ResponseEntity<>(all,HttpStatus.OK);
        throw new ResourceException.NotFound("Currently there is no vote");
    }
    public ResponseEntity<PollResults> getResults(String id){
        return repository.findById(id)
                .map(pollResults -> new ResponseEntity<>(pollResults, HttpStatus.OK))
                .orElseThrow(() -> new ResourceException.NotFound("Currently there is no vote"));
    }
}
