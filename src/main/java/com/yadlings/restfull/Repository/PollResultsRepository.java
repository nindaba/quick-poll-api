package com.yadlings.restfull.Repository;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Domain.PollResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollResultsRepository extends MongoRepository<PollResults,String> {
}
