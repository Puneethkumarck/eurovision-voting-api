package com.pega.votingapi.service;

import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.model.VoteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class VoteServiceImplTest {

    @Autowired
    private VotingService votingService;

    @Test
    void createVote() {
        VoteRequest voteRequest = VoteRequest.of("Netherlands","Sweden","2022");
        Vote vote=votingService.createVote(voteRequest);
        assertNotNull(vote);
    }

    @Test
    void retrieveTopThreeCountryWithMaximumVotes() {
        Map<String,String> topThreeResponse=votingService.retrieveTopThreeCountryWithMaximumVotes("2022");
        assertNotNull(topThreeResponse);
    }

    @Test
    void retrieveTopThreeFavSongs() {
        Map<String,String> topThreeResponse=votingService.retrieveTopThreeFavSongs("2022","Romania");
        assertNotNull(topThreeResponse);
    }

}