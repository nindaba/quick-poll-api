package com.yadlings.restfull.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Data@NoArgsConstructor@AllArgsConstructor@Document
public class User {
    @Id
    private String id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String username;
    @NotEmpty
    private Boolean admin;
    @JsonIgnore
    @NotEmpty
    @Length(min = 8 ,max = 15)

    private String password;
}
