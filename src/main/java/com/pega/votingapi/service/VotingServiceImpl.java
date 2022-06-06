package com.pega.votingapi.service;

import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.model.TopThreeResponse;
import com.pega.votingapi.model.VoteRequest;
import com.pega.votingapi.persistence.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class VotingServiceImpl implements VotingService{

    private VoteRepository voteRepository;

    public VotingServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Vote createVote(VoteRequest voteRequest) {
        Vote voteTableRequest = Vote.builder().countryFrom
                        (voteRequest.getCountryFrom()).
                votedFor(voteRequest.getVotedFor()).votingYear(String.valueOf(LocalDate.now().getYear())).build();

        return voteRepository.save(voteTableRequest);
    }


    @Override
    public TopThreeResponse retrieveTopThreeCountryWithMaximumVotes(String year) {
        Optional<List<String>> response = voteRepository.findTopThreeVotes(year);
        TopThreeResponse topThreeVoteResponse = null;
        if (response.isPresent()) {
            topThreeVoteResponse = buildResponse(response.get());
        }
        return topThreeVoteResponse;
    }


    @Override
    public TopThreeResponse retrieveTopThreeFavSongs(String year,String country){
        Optional<List<String>> response = voteRepository.findTopThreeFavSongs(year,country);
        TopThreeResponse topThreeVoteResponse = null;
        if (response.isPresent()) {
            topThreeVoteResponse = buildResponse(response.get());
        }
        return topThreeVoteResponse;
    }


    /**
     * @param responseList List<String>
     * @return TopThreeResponse
     */
    TopThreeResponse buildResponse(List<String> responseList){
        TopThreeResponse topThreeVoteResponse = new TopThreeResponse();
        if (responseList.size() >= 3) {
            topThreeVoteResponse.setFirst(responseList.get(0));
            topThreeVoteResponse.setSecond(responseList.get(1));
            topThreeVoteResponse.setThird(responseList.get(2));
        } else if ( responseList.size()>= 2) {
            topThreeVoteResponse.setFirst(responseList.get(0));
            topThreeVoteResponse.setSecond(responseList.get(1));
        } else if ( responseList.size()>= 1){
            topThreeVoteResponse.setFirst(responseList.get(0));
        } else{
            topThreeVoteResponse=null;
        }
        return topThreeVoteResponse;
    }
}

