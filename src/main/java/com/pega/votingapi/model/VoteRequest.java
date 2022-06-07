package com.pega.votingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")

public class VoteRequest {

    @NotEmpty(message = "countryFrom should not be blank")
    @Pattern(regexp = "^[a-zA-Z]*$",message = "should contains only alphabets")
    private String countryFrom;

    @NotEmpty(message = "votedFor should not be blank")
    @Pattern(regexp = "^[a-zA-Z]*$",message = "should contains only alphabets")
    private String votedFor;

    private String votingYear;
}

