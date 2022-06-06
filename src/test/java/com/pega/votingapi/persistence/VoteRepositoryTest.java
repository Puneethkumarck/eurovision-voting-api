package com.pega.votingapi.persistence;

import com.pega.votingapi.entity.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


@DataJpaTest
class VoteRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void findTopThreeVotes() {
        Optional<List<String>> topThreeCountries = voteRepository.findTopThreeVotes("2022");
        assertThat(topThreeCountries.get().size(), is(3));
        assertThat(topThreeCountries.get().get(0), is("Germany"));
        assertThat(topThreeCountries.get().get(1), is("Italy"));
        assertThat(topThreeCountries.get().get(2), is("Romania"));
    }

    @Test
    void findTopThreeVotesNotFound() {
        Optional<List<String>> topThreeCountries = voteRepository.findTopThreeVotes("2000");
        assertThat(topThreeCountries.get().size(), is(0));
    }

    @Test
    void findTopThreeFavSongs() {
        Optional<List<String>> topThreeCountries = voteRepository.findTopThreeFavSongs("2022", "Germany");
        assertThat(topThreeCountries.get().size(), is(3));
        assertThat(topThreeCountries.get().get(0), is("France"));
        assertThat(topThreeCountries.get().get(1), is("Belgium"));
        assertThat(topThreeCountries.get().get(2), is("Netherlands"));
    }

    @Test
    void findTopThreeFavSongsNotFound() {
        Optional<List<String>> topThreeCountries = voteRepository.findTopThreeFavSongs("202", "Germany");
        assertThat(topThreeCountries.get().size(), is(0));
    }


    @BeforeEach
    void setUp() {
        Vote vote1 = Vote.builder().countryFrom("France").votedFor("Germany").votingYear("2022").build();
        Vote vote2 = Vote.builder().countryFrom("France").votedFor("Germany").votingYear("2022").build();
        Vote vote3 = Vote.builder().countryFrom("France").votedFor("Italy").votingYear("2022").build();
        Vote vote4 = Vote.builder().countryFrom("France").votedFor("Romania").votingYear("2022").build();
        Vote vote5 = Vote.builder().countryFrom("Netherlands").votedFor("Germany").votingYear("2022").build();
        Vote vote6 = Vote.builder().countryFrom("Belgium").votedFor("Germany").votingYear("2022").build();
        List<Vote> voteList = List.of(vote1, vote2, vote3, vote4, vote5, vote6);
        voteList.forEach(vote -> voteRepository.save(vote));
    }
}