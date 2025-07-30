package com.voting.VotingPollApp.model;

import lombok.Data;

@Data
public class UsersDTO {
    private String name;
    private String role;
    private String e_mail;

    public UsersDTO(String name, String role, String e_mail) {
        this.name = name;
        this.role = role;
        this.e_mail = e_mail;
    }
}
