package com.pega.votingapi.service;

import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.model.VoteRequest;
import com.pega.votingapi.persistence.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;


@Service
@Transactional(readOnly = true)
public class VotingServiceImpl implements VotingService{

    private VoteRepository voteRepository;

    public VotingServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    static String[] topThree = {"first","second","third"};

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Vote createVote(VoteRequest voteRequest) {
        Vote voteTableRequest = Vote.builder().countryFrom
                        (voteRequest.getCountryFrom()).
                votedFor(voteRequest.getVotedFor()).votingYear(voteRequest.getVotingYear()).build();

        return voteRepository.save(voteTableRequest);
    }


    @Override
    public Map<String,String> retrieveTopThreeCountryWithMaximumVotes(String year) {
        Optional<List<String>> response = voteRepository.findTopThreeVotes(year);
        Map<String,String> topThreeVoteResponse = Collections.emptyMap();
        if (response.isPresent())
            topThreeVoteResponse = buildResponse(response.get());

        return topThreeVoteResponse;
    }


    @Override
    public Map<String,String> retrieveTopThreeFavSongs(String year,String country){
        Optional<List<String>> response = voteRepository.findTopThreeFavSongs(year,country);
        Map<String,String> topThreeVoteResponse = Collections.emptyMap();
        if (response.isPresent())
            topThreeVoteResponse = buildResponse(response.get());

        return topThreeVoteResponse;
    }



    Map<String,String> buildResponse(List<String> responseList){
        Map<String,String> responseMap = new LinkedHashMap<>();
        for(int i=0;i<responseList.size();i++){
            responseMap.put(topThree[i],responseList.get(i));
        }
        return responseMap;
    }
}

