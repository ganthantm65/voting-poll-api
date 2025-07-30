package com.voting.VotingPollApp.controller;

import com.voting.VotingPollApp.UserService.VoteService;
import com.voting.VotingPollApp.model.Vote;
import com.voting.VotingPollApp.model.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/votePoll")
    public ResponseEntity<?> votePoll(@RequestBody Vote vote){
        return voteService.votePoll(vote);
    }
    @GetMapping("/getVotedPolls")
    public List<VoteDTO> getVotedPolls(){
        return voteService.getVotedPolls();
    }
}
