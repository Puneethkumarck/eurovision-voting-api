package com.pega.votingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class VoteRequest {

    @NotEmpty(message = "countryFrom should not be blank")
    private String countryFrom;

    @NotEmpty(message = "votedFor should not be blank")
    private String votedFor;
}

