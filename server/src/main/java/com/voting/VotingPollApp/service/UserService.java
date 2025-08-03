package com.voting.VotingPollApp.service;

import com.voting.VotingPollApp.model.UserModel;
import com.voting.VotingPollApp.model.UsersDTO;
import com.voting.VotingPollApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> addUser(UserModel userModel){
        try{
            if(userModel.getRole().equals("ADMIN")){
                throw new Exception("Adding admin in user");
            }
            Map<String,String> message=new HashMap<>();
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userRepo.save(userModel);
            message.put("message","Account Created Successfully");
            return ResponseEntity.status(201).body(message);
        }catch (Exception e){
            Map<String,String> message=new HashMap<>();
            message.put("message",e.getMessage());
            return ResponseEntity.status(400).body(message);
        }
    }

    public ResponseEntity<?> addAdmin(UserModel userModel){
        try{
            if(userModel.getRole().equals("USER")){
                throw new Exception("Adding user in admin");
            }
            Map<String,String> message=new HashMap<>();
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userRepo.save(userModel);
            message.put("message","Account Created Successfully");
            return ResponseEntity.status(201).body(message);
        }catch (Exception e){
            Map<String,String> message=new HashMap<>();
            message.put("message",e.getMessage());
            return ResponseEntity.status(400).body(message);
        }
    }
    public Integer getUserId(UserModel userModel){
        return userRepo.findUserName(userModel.getUserName());
    }
    public List<UsersDTO> getUsers(){
        return userRepo.getUsers();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel=userRepo.findUserByUserName(username);
        if(userModel==null){
            throw new UsernameNotFoundException("User not found with username"+username);
        }
        return User.builder()
                .username(userModel.getUserName())
                .password(userModel.getPassword())
                .roles(userModel.getRole())
                .build();
    }
}
