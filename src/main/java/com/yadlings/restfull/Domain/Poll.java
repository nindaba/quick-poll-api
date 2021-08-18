package com.yadlings.restfull.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@ApiModel(value = "Poll",description = "Poll Model")
public class Poll {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotEmpty
    @ApiModelProperty(required = true)
    private String question;
    @Size(min = 2,max = 6)
    @NotNull
    @ApiModelProperty(required = true,notes = "The Options must be more than 2 and less tha 6")
    private Set<Option> options;
    private long timestamp;
}
