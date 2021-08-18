package com.yadlings.restfull;

import com.yadlings.restfull.Domain.Option;
import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Domain.QuickPage;
import lombok.extern.log4j.Log4j2;
import org.bson.internal.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class QuickPollClient {
    private RestTemplate restTemplate= new RestTemplate();
    private String QUICK_POLL_URI_V1 = "http://localhost:6001/v1/poll";
    private String QUICK_POLL_URI_V2 = "http://localhost:6001/v2/poll";
    public Poll getPollById(String id){
        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/{id}", Poll.class, id);
    }
    public List<Poll> getPoll(){
        return Arrays.asList(restTemplate.getForObject(QUICK_POLL_URI_V1 , Poll[].class));
    }
    public URI createPoll(Poll poll){
        return restTemplate.postForLocation(QUICK_POLL_URI_V1, poll);
    }
    private void update(Poll newPoll) {
        restTemplate.put(QUICK_POLL_URI_V1+"/{id}",newPoll,newPoll.getId());
    }
    private QuickPage<Poll> getPage(String page){
        var response = new ParameterizedTypeReference<QuickPage<Poll>>(){};
        ResponseEntity<QuickPage<Poll>> exchange = restTemplate.exchange(QUICK_POLL_URI_V2 + "?" + page, HttpMethod.GET, null, response);
        return exchange.getBody();
    }
    public static void main(String[] args) {
        QuickPollClient quickPollClient = new QuickPollClient();
        Poll pollById = quickPollClient.getPollById("611377b7537dc656deda2eff");
        log.info("Fetched {}",pollById);
        log.info("Fetched {}",quickPollClient.getPoll());

        Poll newPoll = new Poll();
        newPoll.setId("611377b7537dc656deda2eff");
        newPoll.setQuestion("What is your favourite color dragon?");
        Set<Option> options = new HashSet<>();
        newPoll.setOptions(options);
        Option option1 = new Option();
        option1.setValue("Red"); options.add(option1);
        Option option2 = new Option();
        option2.setValue("Blue");options.add(option2);
//        URI poll = quickPollClient.createPoll(newPoll);
        quickPollClient.update(newPoll);
        Poll poll = quickPollClient.getPollById(newPoll.getId());
        log.info("Posted {}",poll);

        log.info("Page {}",quickPollClient.getPage("page=1&size=2&sort=id"));
    }

}

