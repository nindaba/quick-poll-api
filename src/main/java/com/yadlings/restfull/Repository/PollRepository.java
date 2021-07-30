package com.yadlings.restfull.Repository;

import com.yadlings.restfull.Domain.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends MongoRepository<Poll,String> {
    Poll 
}
