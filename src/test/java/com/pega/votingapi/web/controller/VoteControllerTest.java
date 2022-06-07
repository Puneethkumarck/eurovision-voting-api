package com.pega.votingapi.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.model.VoteRequest;
import com.pega.votingapi.service.VotingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Map;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VoteController.class)
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class VoteControllerTest {

    @MockBean
    private VotingService voteService;

    @InjectMocks
    private VoteController voteController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Vote vote = Vote.builder().countryFrom("Netherlands").votedFor("Belgium").votingYear("2022").build();

    private final Map<String,String> topThreeResponse = Map.of("first","Germany","Second",
            "Italy","third","France");

    private final Map<String,String> topThreeFavSongResponse = Map.of("first","Netherlands","Second",
            "France","third","Belgium");

    @Test
    void createVote() throws Exception {

        //given
        VoteRequest voteRequest=VoteRequest.of("Netherlands","Belgium","2022");

        //when
        when(voteService.createVote(voteRequest)).thenReturn(vote);

        //then
        mockMvc.perform(post("/{year}","2022").contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(voteRequest))).andDo(print()).andExpect(status().isCreated()).andDo(document("createVote"));
    }


    @Test
    void createVote_InvalidArgument() throws Exception {
        //given
        VoteRequest voteRequest=VoteRequest.of("","","");

        //when
        when(voteService.createVote(voteRequest)).thenReturn(vote);

        //then
        mockMvc.perform(post("/{year}","2022").contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(voteRequest))).andDo(print()).
                andExpect(status().isBadRequest()).andDo(document("createVote_404"));
    }

    @Test
    void getTopThreeVotes() throws Exception {

        //when
        when(voteService.retrieveTopThreeCountryWithMaximumVotes(anyString())).thenReturn(topThreeResponse);

        //then
        mockMvc.perform(get("/2022").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isOk()).andDo(document("getTopThreeCountryWithMaxiumVote"));
    }


    @Test
    void getTopThreeVotes_notfound() throws Exception {

        //when
        when(voteService.retrieveTopThreeCountryWithMaximumVotes("")).thenReturn(topThreeResponse);

        //then
        mockMvc.perform(get("/2022").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isNotFound()).andDo(document("getTopThreeCountryWithMaxiumVote_404"));
    }


    @Test
    void getTopThreeFavSongs() throws Exception {

        //when
        when(voteService.retrieveTopThreeFavSongs(anyString(),anyString())).thenReturn(topThreeFavSongResponse);

        //then
        mockMvc.perform(get("/2022/Netherlands").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isOk()).andDo(document("getTopThreeFavSongs"));
    }


    @Test
    void getTopThreeFavSongs_notfound() throws Exception {

        //when
        when(voteService.retrieveTopThreeFavSongs("","")).thenReturn(topThreeFavSongResponse);

        //then
        mockMvc.perform(get("/2022/Netherlands").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isNotFound()).andDo(document("getTopThreeFavSongs_404"));
    }
}