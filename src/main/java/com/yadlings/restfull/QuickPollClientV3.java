package com.yadlings.restfull;

import com.yadlings.restfull.Domain.Option;
import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Domain.QuickPage;
import lombok.extern.log4j.Log4j2;
import org.bson.internal.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class QuickPollClientV3 {
    private RestTemplate restTemplate= new RestTemplate();
    private String QUICK_POLL_URI_V3 = "http://localhost:6001/v3/poll";
    private HttpHeaders getSecurityHeaders(String username,String password){
        String credentials =  username+":"+password;
        String encode = Base64.encode(credentials.getBytes(StandardCharsets.UTF_8));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic "+new String(encode));
        return httpHeaders;
    }
    private List<Poll> getPoll(){
        HttpEntity<Object> entity = new HttpEntity<>(getSecurityHeaders("ab", "ab123456"));
        ResponseEntity<Poll[]> exchange = restTemplate.exchange(QUICK_POLL_URI_V3, HttpMethod.GET, entity, Poll[].class);
        return Arrays.asList(exchange.getBody());
    }
    public static void main(String[] args) {
        QuickPollClientV3 clientV3 = new QuickPollClientV3();
        log.info("Secured Poll {}",clientV3.getPoll());
    }

}

