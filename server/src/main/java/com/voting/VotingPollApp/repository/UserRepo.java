package com.voting.VotingPollApp.repository;

import com.voting.VotingPollApp.model.UserModel;
import com.voting.VotingPollApp.model.UsersDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    UserModel findUserByUserName(String userName);
    @Query("SELECT u.user_id FROM UserModel u WHERE u.userName = :userName")
    Integer findUserName(@Param("userName") String userName);

    @Query("SELECT new com.voting.VotingPollApp.model.UsersDTO(u.userName,u.role,u.e_mail) FROM UserModel u")
    List<UsersDTO> getUsers();
}
