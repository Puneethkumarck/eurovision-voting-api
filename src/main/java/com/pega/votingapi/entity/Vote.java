package com.pega.votingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String countryFrom;

    private String votedFor;

    private String votingYear;

    public Vote(String countryFrom, String votedFor, String votingYear) {
        this.countryFrom = countryFrom;
        this.votedFor = votedFor;
        this.votingYear = votingYear;
    }
}
