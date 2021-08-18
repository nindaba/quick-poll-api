package com.yadlings.restfull;

import com.yadlings.restfull.Domain.Poll;
import lombok.extern.log4j.Log4j2;
import org.bson.internal.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class QuickPollClientV3Oauth {
    private String QUICK_POLL_URI_V3 = "http://localhost:6001/oauth2/v3/poll";
    private OAuth2RestTemplate restTemplate(){
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setPassword("ab123456");
        resourceDetails.setUsername("ab");
        resourceDetails.setClientId("ios_client");
        resourceDetails.setClientSecret("ios_sec");
        resourceDetails.setGrantType("password");
        resourceDetails.setScope(Arrays.asList("read","write"));
        resourceDetails.setAccessTokenUri("http://localhost:6001/oauth/token");
        return new OAuth2RestTemplate(resourceDetails);
    }
    private List<Poll> getPoll(){
        Poll[] entity = restTemplate().getForObject(QUICK_POLL_URI_V3, Poll[].class);
        return Arrays.asList(entity);
    }
    public static void main(String[] args) {
        QuickPollClientV3Oauth clientV3 = new QuickPollClientV3Oauth();
        log.info("Secured Poll {}",clientV3.getPoll());
    }

}

