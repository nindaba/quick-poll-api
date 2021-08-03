package com.yadlings.restfull.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ValidationError {
    private String code;
    private String message;
}
