package com.yadlings.restfull.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
@AllArgsConstructor
public class Sort{
    private Boolean sorted;
    private Boolean unsorted;
    private Boolean empty;
}
