package com.voting.VotingPollApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vote_id;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Options options;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
}
