package com.voting.VotingPollApp.controller;

import com.voting.VotingPollApp.UserService.UserService;
import com.voting.VotingPollApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserModel userModel){
        return userService.addUser(userModel);
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody UserModel userModel){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getUserName(),userModel.getPassword())
        );
        if(authentication.isAuthenticated()){
            UserDetails userDetails=userService.loadUserByUsername(userModel.getUserName());
            Map<String,String> map=new HashMap<>();
            map.put("user_name",userDetails.getUsername());
            return ResponseEntity.ok(map);
        }else {
            Map<String,String> map=new HashMap<>();
            map.put("message","User is not authorized");
            return ResponseEntity.status(401).body(map);
        }
    }
}
