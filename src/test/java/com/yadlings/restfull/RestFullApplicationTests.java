package com.yadlings.restfull;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Exception.RestException;
import com.yadlings.restfull.Repository.PollRepository;
import com.yadlings.restfull.Service.PollService;
import com.yadlings.restfull.v1.Controllers.PollController;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestFullApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Mock
//	private PollService pollService;
//
//	@BeforeAll
//	public void init() throws Exception{
//		MockitoAnnotations.initMocks(this);
//	}
//	@Test
//	public void getAllPoll(){
////		CLASS TO TEST
//		PollController controller = new PollController();
//
////		INITIALIZE MOCK FIELDS
//		ReflectionTestUtils.setField(controller,"pollService",pollService);
////		CALLS
//		when(pollService.getPoll()).thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));
//		ResponseEntity<List<Poll>> polls = controller.getPoll();
//
////		VERIFY CALLS
//		verify(pollService,times(1)).savePoll(new Poll());
//
////		CHECK RESULTS MATCH
//		assertEquals(HttpStatus.OK,polls.getStatusCode());
//		assertEquals(0,polls.getBody().size());
//	}
}
