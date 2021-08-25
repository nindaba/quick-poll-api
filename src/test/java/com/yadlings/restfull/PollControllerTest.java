package com.yadlings.restfull;

import com.yadlings.restfull.Domain.Poll;
import com.yadlings.restfull.Repository.PollRepository;
import com.yadlings.restfull.Service.PollService;
import com.yadlings.restfull.v1.Controllers.PollController;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
@SpringBootTest
@ContextConfiguration(classes = WebApplicationContext.class)

@RunWith(JUnit4ClassRunner.class)
@WebAppConfiguration
public class PollControllerTest{
    MockMvc mockMvc;
    @Mock
    public PollService pollService;
    @InjectMocks
    public PollController pollController;
    @Before
    public void Setup() throws Exception{
        mockMvc = standaloneSetup(pollController).build();
    }
    @Test
    public void deletePoll() throws Exception{
        mockMvc.perform(delete("/v1/poll./y7u2wef6eu"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
