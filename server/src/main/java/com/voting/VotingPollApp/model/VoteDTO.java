package com.voting.VotingPollApp.model;

public class VoteDTO {
    private String question;
    private String option_name;
    private String user_name;
    private int user_id;
    private int vote_id;

    public VoteDTO(String question, String option_name, String user_name,int user_id, int vote_id) {
        this.question = question;
        this.option_name = option_name;
        this.user_name = user_name;
        this.user_id=user_id;
        this.vote_id = vote_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
