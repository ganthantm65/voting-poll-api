package com.voting.VotingPollApp.service;

import com.voting.VotingPollApp.model.Options;
import com.voting.VotingPollApp.model.Poll;
import com.voting.VotingPollApp.repository.OptionRepo;
import com.voting.VotingPollApp.repository.PollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OptionService {
    @Autowired
    private OptionRepo optionRepo;

    @Autowired
    private PollRepo pollRepo;

    public void addOptions(List<Options> options, int poll_id){
        Poll poll = pollRepo.findById(poll_id)
                .orElseThrow(() -> new RuntimeException("Poll not found with id " + poll_id));
        for(Options option : options){
            option.setPoll(poll);
            optionRepo.save(option);
        }
    }
    public void deleteOptionsByPollId(int pollId) {
        optionRepo.deleteByPoll(pollId);
    }
}
