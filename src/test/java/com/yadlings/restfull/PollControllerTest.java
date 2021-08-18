package com.yadlings.restfull;

import com.yadlings.restfull.Repository.PollRepository;
import com.yadlings.restfull.Service.PollService;
import com.yadlings.restfull.v1.Controllers.PollController;
import org.junit.Before;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(JUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class PollControllerTest {
    @InjectMocks
    PollController pollController;
    @Mock
    private PollRepository pollRepository;

    private MockMvc mockMvc;
    @Before

    private  void  setUp(){

    }

}
