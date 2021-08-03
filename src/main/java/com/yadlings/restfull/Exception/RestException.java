package com.yadlings.restfull.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestException {
    private String title;
    private int status;
    private String devDetails;
    private long timestamp;
    private String message;
    private String path;
    private Map<String,ValidationError> errors = new HashMap<>();
    static class Builder{
        private RestException restException;
        Builder(){
            restException = new RestException();
        }
        public Builder setTitle(String title){
            restException.setTitle(title);
            return this;
        }
        public Builder setStatus(int httpStatus){
            restException.setStatus(httpStatus);
            return this;
        }
        public Builder setDevDetails(String details){
            restException.setDevDetails(details);
            return this;
        }
        public Builder setTimestamp(long timestamp){
            restException.setTimestamp(timestamp);
            return this;
        }
        public Builder setMessage(String message){
            restException.setMessage(message);
            return this;
        }
        public Builder setPath(String path){
            restException.setPath(path);
            return this;
        }
        public Builder setErrors(Map<String,ValidationError> errors){
            restException.setErrors(errors);
            return this;
        }
        public RestException build(){
            return restException;
        }
    }
}
