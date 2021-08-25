package com.yadlings.restfull.Repository;

import com.yadlings.restfull.Domain.UserVoted;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVotedRepository extends MongoRepository<UserVoted,String> {
}
