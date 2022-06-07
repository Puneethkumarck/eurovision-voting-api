package com.pega.votingapi.web.controller;

import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.model.VoteRequest;
import com.pega.votingapi.service.VotingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;


@RestController
public class VoteController {

    private VotingService voteService;

    public VoteController(VotingService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = "/{year}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteRequest> createVote(@PathVariable @NotBlank String year,@RequestBody @Valid VoteRequest voteRequest){
        voteRequest.setVotingYear(year);
        Vote _vote = voteService.createVote(voteRequest);
        return  new ResponseEntity<>(VoteRequest.of(_vote.getCountryFrom(),_vote.getVotedFor(),_vote.getVotingYear()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{year}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> getTopThreeVotes(@PathVariable @NotBlank String year){
        Map<String,String>  topThreeVoteResponse = voteService.retrieveTopThreeCountryWithMaximumVotes(year);
        if(ObjectUtils.isEmpty(topThreeVoteResponse))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format(TOP_THREE_COUNTRY_FOR_GIVEN_YEAR_NOT_FOUND, year));
        return new ResponseEntity<>(topThreeVoteResponse,HttpStatus.OK);
    }

    @GetMapping(value = "/{year}/{country}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> getTopThreeFavSongs(@PathVariable @NotBlank String year, @PathVariable @NotBlank String country){
        Map<String,String> topThreeVoteResponse = voteService.retrieveTopThreeFavSongs(year,country);
        if(ObjectUtils.isEmpty(topThreeVoteResponse))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format(TOP_THREE_FAV_SONGS_FOR_GIVEN_YEAR_AND_COUNTRY_NOT_FOUND, year,country));
        return new ResponseEntity<>(topThreeVoteResponse,HttpStatus.OK);
    }


    private static final String TOP_THREE_COUNTRY_FOR_GIVEN_YEAR_NOT_FOUND = "No results found for given input year %s";

    private static final String TOP_THREE_FAV_SONGS_FOR_GIVEN_YEAR_AND_COUNTRY_NOT_FOUND = "No results found for given input year %s and country %s";
}
