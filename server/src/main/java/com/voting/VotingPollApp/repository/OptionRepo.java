package com.voting.VotingPollApp.repository;

import com.voting.VotingPollApp.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionRepo extends JpaRepository<Options, Integer> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Options o WHERE o.poll.poll_id = :poll_id")
    void deleteByPoll(@Param("poll_id") int poll_id);


    @Query("SELECT o FROM Options o WHERE o.poll.poll_id = :pollId")
    List<Options> findOptionByPollId(@Param("pollId") int pollId);
}

