package com.voting.VotingPollApp.repository;


import com.voting.VotingPollApp.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PollRepo extends JpaRepository<Poll,Integer> {
    @Query("SELECT p FROM Poll p WHERE p.user.user_id = :user_id")
    List<Poll> findByUserId(@Param("user_id") int user_id);
}
