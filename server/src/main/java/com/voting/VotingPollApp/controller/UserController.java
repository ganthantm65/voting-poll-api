package com.voting.VotingPollApp.controller;

import com.voting.VotingPollApp.UserService.UserService;
import com.voting.VotingPollApp.config.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registerUser")
    public ResponseEntity<?> addUser(@RequestBody UserModel userModel){
        return userService.addUser(userModel);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody UserModel userModel){return userService.addAdmin(userModel);}

    @PostMapping("/loginUser")
    public ResponseEntity<?> getUser(@RequestBody UserModel userModel){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getUserName(),userModel.getPassword())
        );
        if(authentication.isAuthenticated() && "USER".equals(userModel.getRole())){
            UserDetails userDetails=userService.loadUserByUsername(userModel.getUserName());
            String token=jwtUtil.generateToken(userDetails);
            Integer id=userService.getUserId(userModel);
            Map<String,String> map=new HashMap<>();
            map.put("user_name",userDetails.getUsername());
            map.put("user_id",id.toString());
            map.put("e_mail",userModel.getE_mail());
            map.put("token",token);
            return ResponseEntity.ok(map);
        }else {
            Map<String,String> map=new HashMap<>();
            map.put("message","User is not authorized");
            return ResponseEntity.status(403).body(map);
        }
    }
    @PostMapping("/loginAdmin")
    public ResponseEntity<?> getAdmin(@RequestBody UserModel userModel){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getUserName(),userModel.getPassword())
        );
        if(authentication.isAuthenticated() && "ADMIN".equals(userModel.getRole())){
            UserDetails userDetails=userService.loadUserByUsername(userModel.getUserName());
            String token=jwtUtil.generateToken(userDetails);
            Integer id=userService.getUserId(userModel);
            Map<String,String> map=new HashMap<>();
            map.put("user_name",userDetails.getUsername());
            map.put("user_id",id.toString());
            map.put("e_mail",userModel.getE_mail());
            map.put("token",token);
            return ResponseEntity.ok(map);
        }else {
            Map<String,String> map=new HashMap<>();
            map.put("message","User is not authorized");
            return ResponseEntity.status(403).body(map);
        }
    }
}
