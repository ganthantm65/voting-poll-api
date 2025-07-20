package com.voting.VotingPollApp.repository;

import com.voting.VotingPollApp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    @Query(value = "SELECT * FROM users WHERE user_name = :userName AND role = :role", nativeQuery = true)
    UserModel findUserByUserName(@Param("userName") String userName, @Param("role") String role);}
