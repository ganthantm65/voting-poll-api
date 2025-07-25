package com.voting.VotingPollApp.repository;

import com.voting.VotingPollApp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    UserModel findUserByUserName(String userName);}
