package com.pega.votingapi.service;

import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.model.VoteRequest;
import java.util.Map;

public interface VotingService {

    /**
     * Retrieves Top three Fav songs for given country and year
     * @param voteRequest VoteRequest
     * @return TopThreeResponse
     */
    Vote createVote(VoteRequest voteRequest);

    /**
     *  Retrieves Top three country with maximum Votes songs for given country and year
     * @param year String
     * @return TopThreeResponse
     */
    Map<String,String> retrieveTopThreeCountryWithMaximumVotes(String year);

    /**
     *  Retrieves Top three Fav songs for given country and year
     * @param year String
     * @param country String
     * @return TopThreeResponse
     */
    Map<String,String> retrieveTopThreeFavSongs(String year, String country);

}
