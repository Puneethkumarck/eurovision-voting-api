package com.pega.votingapi.config;

import com.pega.votingapi.entity.Vote;
import com.pega.votingapi.persistence.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class LoadDataBase implements CommandLineRunner {

    private VoteRepository voteRepository;

    public LoadDataBase(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("loading database..");
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Russia").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Netherlands").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Germany").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Germany").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("Italy").votedFor("Romania").votingYear("2022").build());
        voteRepository.save(Vote.builder().countryFrom("France").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("France").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("France").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("France").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("France").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("Germany").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("Germany").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("Germany").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("Germany").votedFor("Romania").votingYear("1989").build());
        voteRepository.save(Vote.builder().countryFrom("Germany").votedFor("France").votingYear("2021").build());
        voteRepository.save(Vote.builder().countryFrom("Italy").votedFor("Romania").votingYear("2021").build());
        voteRepository.save(Vote.builder().countryFrom("Italy").votedFor("Romania").votingYear("2021").build());
        voteRepository.save(Vote.builder().countryFrom("Italy").votedFor("Romania").votingYear("2021").build());
        voteRepository.save(Vote.builder().countryFrom("Italy").votedFor("Romania").votingYear("2021").build());
        voteRepository.save(Vote.builder().countryFrom("Italy").votedFor("Romania").votingYear("2021").build());
        voteRepository.save(Vote.builder().countryFrom("Italy").votedFor("Romania").votingYear("2021").build());
        voteRepository.save(Vote.builder().countryFrom("France").votedFor("Romania").votingYear("2021").build());
        voteRepository.findAll().forEach(x -> log.debug(x.toString()));
    }
}

