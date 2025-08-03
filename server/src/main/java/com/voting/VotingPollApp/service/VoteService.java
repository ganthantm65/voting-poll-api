package com.voting.VotingPollApp.service;

import com.voting.VotingPollApp.model.Vote;
import com.voting.VotingPollApp.model.VoteDTO;
import com.voting.VotingPollApp.repository.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VoteService {
    @Autowired
    private VoteRepo voteRepo;

    public ResponseEntity<?> votePoll(Vote vote){
        try {
            voteRepo.save(vote);
            Map<String,String> map=new HashMap<>();
            map.put("message","voted poll successfully");
            return ResponseEntity.status(201).body(map);
        } catch (Exception e) {
            Map<String,String> map=new HashMap<>();
            map.put("message",e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(400).body(map);
        }
    }

    public List<VoteDTO> getVotedPolls(){
        return voteRepo.getAllVotesAsDTO();
    }
}
