package com.voting.VotingPollApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;

import java.util.List;

@Entity
@Data
@Table(name = "poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int poll_id;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Options> optionList;

    public enum PollStatus {
        active,
        inactive
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PollStatus status;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}
