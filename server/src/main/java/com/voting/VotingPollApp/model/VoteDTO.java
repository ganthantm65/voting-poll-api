package com.voting.VotingPollApp.model;

public class VoteDTO {
    private String question;
    private String option_name;
    private String user_name;
    private int vote_id;

    public VoteDTO(String question, String option_name, String user_name, int vote_id) {
        this.question = question;
        this.option_name = option_name;
        this.user_name = user_name;
        this.vote_id = vote_id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption_name() {
        return option_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public int getVote_id() {
        return vote_id;
    }
}
