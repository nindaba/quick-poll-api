package com.yadlings.restfull.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor@AllArgsConstructor@Data
@Document
public class UserVoted {
    @Id
    private String userId;
    private List<String> polls;
}
