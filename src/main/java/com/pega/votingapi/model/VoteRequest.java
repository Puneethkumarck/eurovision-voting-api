package com.pega.votingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class VoteRequest {

    @NonNull
    private String countryFrom;

    @NonNull
    private String votedFor;
}

