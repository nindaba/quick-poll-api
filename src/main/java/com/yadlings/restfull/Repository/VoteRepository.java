package com.yadlings.restfull.Repository;

import com.yadlings.restfull.Domain.Vote;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends MongoRepository<Vote,String> {
    List<Vote> findByPollId(String pollId);
}
