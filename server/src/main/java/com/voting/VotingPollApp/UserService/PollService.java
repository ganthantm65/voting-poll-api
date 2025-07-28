package com.voting.VotingPollApp.UserService;

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
public class PollService {
    @Autowired
    private PollRepo pollRepo;

    @Autowired
    private OptionRepo optionsRepo;

    public Poll addPoll(Poll poll){
        return pollRepo.save(poll);
    }

    public List<Poll> getAllPoll(int user_id) {
        List<Poll> polls = pollRepo.findByUserId(user_id);

        for (Poll poll : polls) {
            List<Options> choiceNames = optionsRepo.findOptionByPollId(poll.getPoll_id());
            poll.setOptionList(choiceNames);
        }

        return polls;
    }

    public Poll updatePoll(Poll poll){
        if (!pollRepo.existsById(poll.getPoll_id())) {
            throw new IllegalArgumentException("Invalid poll ID.");
        }

        Poll existingPoll = pollRepo.findById(poll.getPoll_id()).get();
        existingPoll.setQuestion(poll.getQuestion());
        existingPoll.setStatus(poll.getStatus());

        return pollRepo.save(existingPoll);
    }
    public void deletePoll(int poll_id){
        pollRepo.deleteById(poll_id);
    }
}
