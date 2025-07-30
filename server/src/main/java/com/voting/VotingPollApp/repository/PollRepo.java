package com.voting.VotingPollApp.repository;


import com.voting.VotingPollApp.model.Poll;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PollRepo extends JpaRepository<Poll,Integer> {
    @Query("SELECT p FROM Poll p WHERE p.user.user_id = :user_id")
    List<Poll> findByUserId(@Param("user_id") int user_id);

    @Modifying
    @Transactional
    @Query("UPDATE Poll p SET p.status = :status WHERE p.poll_id = :pollId")
    int updatePollStatus(@Param("status") Poll.PollStatus status, @Param("pollId") int pollId);
}
