package com.yadlings.restfull.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SecurityException extends Exception{
    SecurityException(String message){
        super(message);
    }
    SecurityException(String message,Throwable throwable){
        super(message,throwable);
    }
}
