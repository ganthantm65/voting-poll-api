package com.voting.VotingPollApp.repository;

import com.voting.VotingPollApp.model.VoteDTO;
import com.voting.VotingPollApp.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepo extends JpaRepository<Vote, Integer> {

    @Query("SELECT new com.voting.VotingPollApp.model.VoteDTO(" +
            "v.poll.question, v.options.name, v.userModel.userName, v.vote_id) " +
            "FROM Vote v")
    List<VoteDTO> getAllVotesAsDTO();
}
