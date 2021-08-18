package com.yadlings.restfull.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PollResults {
    @Id
    private String id;
    private Map<String,Integer> optionVotes = new HashMap<>();
    private long lastVote;
}
