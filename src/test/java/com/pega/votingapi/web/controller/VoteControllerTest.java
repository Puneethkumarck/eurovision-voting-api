package com.pega.votingapi.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.model.TopThreeResponse;
import com.pega.votingapi.model.VoteRequest;
import com.pega.votingapi.service.VotingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VoteController.class)
class VoteControllerTest {

    @MockBean
    private VotingService voteService;

    @InjectMocks
    private VoteController voteController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Vote vote =
            Vote.builder().countryFrom("Netherlands").votedFor("Belgium").build();

    private final TopThreeResponse topThreeResponse =
            TopThreeResponse.of("Germany","Italy","France");

    @Test
    void createVote() throws Exception {

        //given
        VoteRequest voteRequest=VoteRequest.of("Netherlands","Belgium");

        //when
        when(voteService.createVote(voteRequest)).thenReturn(vote);

        //then
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(voteRequest))).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void getTopThreeVotes() throws Exception {

        //when
        when(voteService.retrieveTopThreeCountryWithMaximumVotes(anyString())).thenReturn(topThreeResponse);

        //then
        mockMvc.perform(get("/2022").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isOk());
    }


    @Test
    void getTopThreeFavSongs() throws Exception {

        //when
        when(voteService.retrieveTopThreeFavSongs(anyString(),anyString())).thenReturn(topThreeResponse);

        //then
        mockMvc.perform(get("/2022/Netherlands").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isOk());
    }
}