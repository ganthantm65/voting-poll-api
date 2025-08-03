package com.voting.VotingPollApp.controller;

import com.voting.VotingPollApp.service.OptionService;
import com.voting.VotingPollApp.service.PollService;
import com.voting.VotingPollApp.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private OptionService optionService;

    @PostMapping("/addPoll")
    public ResponseEntity<?> addPoll(@RequestBody Poll poll) {
        if (poll.getQuestion() == null || poll.getQuestion().length() < 5) {
            return ResponseEntity.badRequest().body("Poll question is too short.");
        }
        if (poll.getOptionList() == null || poll.getOptionList().size() < 2) {
            return ResponseEntity.badRequest().body("At least 2 options are required.");
        }
        if (poll.getUser() == null || poll.getUser().getUser_id() == 0) {
            return ResponseEntity.badRequest().body("User info is missing.");
        }

        try {
            Poll savedPoll = pollService.addPoll(poll);
            System.out.println(savedPoll.getPoll_id());
            optionService.addOptions(poll.getOptionList(), savedPoll.getPoll_id());
            return ResponseEntity.status(201).body(savedPoll);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create poll: " + e.getMessage());
        }
    }

    @PutMapping("/updatePoll")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll) {
        Map<String,String> map=new HashMap<>();
        if (poll.getPoll_id() == 0) {
            map.put("message","Poll ID is required.");
            return ResponseEntity.badRequest().body(map);
        }
        if (poll.getQuestion() == null || poll.getQuestion().length() < 5) {
            map.put("message","Poll question is too short.");
            return ResponseEntity.badRequest().body(map);
        }
        if (poll.getOptionList() == null || poll.getOptionList().size() < 2) {
            map.put("message","At least 2 options are required.");
            return ResponseEntity.badRequest().body(map);
        }
        if (poll.getUser() == null || poll.getUser().getUser_id() == 0) {
            map.put("message","User info is missing.");
            return ResponseEntity.badRequest().body(map);
        }

        try {
            optionService.deleteOptionsByPollId(poll.getPoll_id());
            Poll updatedPoll = pollService.updatePoll(poll);
            optionService.addOptions(poll.getOptionList(),poll.getPoll_id());
            return ResponseEntity.ok(updatedPoll);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to update poll: " + e.getMessage());
        }
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody Poll poll) {
        try {
            if (poll.getPoll_id() == 0 || poll.getStatus() == null) {
                return ResponseEntity.badRequest().body("Poll ID or Status is missing.");
            }

            int updated = pollService.updatePollStatus(poll);
            if (updated > 0) {
                return ResponseEntity.ok("Status updated successfully.");
            } else {
                return ResponseEntity.status(404).body("Poll not found.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
    @GetMapping("/getAllPolls")
    public ResponseEntity<?> getAllPolls(){
        try {
            List<Poll> polls = pollService.getAllPolls();
            return ResponseEntity.ok(polls);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to fetch polls: " + e.getMessage());
        }
    }
    @GetMapping("/getPolls")
    public ResponseEntity<?> getPolls(@RequestParam int user_id) {
        try {
            List<Poll> polls = pollService.getPoll(user_id);
            return ResponseEntity.ok(polls);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to fetch polls: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletePoll/{poll_id}")
    public ResponseEntity<?> deletePoll(@PathVariable int poll_id) {
        try {
            pollService.deletePoll(poll_id);
            optionService.deleteOptionsByPollId(poll_id);
            return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
