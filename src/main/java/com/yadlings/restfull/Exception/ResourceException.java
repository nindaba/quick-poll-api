package com.yadlings.restfull.Exception;
public class ResourceException {

    public static class AlreadyExist extends RuntimeException{
        public AlreadyExist(String message){super(message);}
        public AlreadyExist(String message, Throwable cause){super(message, cause);}
    }
    public static class NotFound extends RuntimeException{
        public NotFound(String message){super(message);}
        public NotFound(String message, Throwable cause){super(message, cause);}
    }
}
