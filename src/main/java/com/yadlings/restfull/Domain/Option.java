package com.yadlings.restfull.Domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Option",description = "Option Model")
public class Option {
    @NotNull
    @ApiModelProperty(required = true)
    private String value;
}
