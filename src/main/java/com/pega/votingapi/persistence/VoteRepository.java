package com.pega.votingapi.persistence;

import com.pega.votingapi.entity.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface VoteRepository extends CrudRepository<Vote,Long> {

    @Query(value = "SELECT VOTED_FOR FROM VOTE WHERE VOTING_YEAR = ?1 group by VOTED_FOR order by count(VOTED_FOR) DESC limit 3", nativeQuery = true)
    Optional<List<String>> findTopThreeVotes(String year);

    @Query(value = "SELECT COUNTRY_FROM FROM VOTE WHERE VOTING_YEAR = ?1 and VOTED_FOR= ?2 group by COUNTRY_FROM order by count(COUNTRY_FROM) DESC limit 3", nativeQuery = true)
    Optional<List<String>> findTopThreeFavSongs(String year, String country);
}