package com.voting.VotingPollApp.repository;

import com.voting.VotingPollApp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    UserModel findUserByUserName(String userName);
    @Query("SELECT u.user_id FROM UserModel u WHERE u.userName = :userName")
    Integer findUserId(@Param("userName") String userName);

}
