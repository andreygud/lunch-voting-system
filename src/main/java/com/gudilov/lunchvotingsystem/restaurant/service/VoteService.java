package com.gudilov.lunchvotingsystem.restaurant.service;

import com.gudilov.lunchvotingsystem.common.exceptions.BusinessRuleViolationException;
import com.gudilov.lunchvotingsystem.restaurant.model.Vote;
import com.gudilov.lunchvotingsystem.restaurant.repository.vote.VoteRepository;
import com.gudilov.lunchvotingsystem.restaurant.service.mapper.VoteMapper;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.checkNotFound;

@Service
public class VoteService {
    public static final LocalTime CUT_OFF_TIME = LocalTime.of(11, 0);
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private VoteMapper voteMapper;
    private VoteRepository voteRepository;

    public VoteService(VoteMapper voteMapper, VoteRepository voteRepository) {
        this.voteMapper = voteMapper;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public VoteViewTo vote(int userId, int restaurantId) {
        log.debug("vote userId={} , restId={}", userId,restaurantId);
        if (LocalTime.now().isAfter(CUT_OFF_TIME)) {
            throw new BusinessRuleViolationException("You cannot vote after 11:00");
        }

        List<Vote> votes = voteRepository.getAllBetweenByUserId(userId,
                LocalDateTime.of(LocalDate.now(),
                        LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));

        Vote vote;
        switch (votes.size()) {
            case 0:
                vote = new Vote(LocalDateTime.now());
                break;
            case 1:
                vote = votes.get(0);
                vote.setVoteTime(LocalDateTime.now());
                break;
            default:
                throw new IllegalStateException("There are more than 1 vote per day in the database. Please contact your administrator.");
        }

        Vote voteResult = checkNotFound(voteRepository.save(vote, userId, restaurantId),"Vote didn't go through. Please contact your administrator.");
        return voteMapper.transformEntityIntoViewTo(voteResult);
    }

    @Transactional
    public VoteViewTo getLast(int userId){
        log.debug("getLast userId={}", userId);
        Vote vote = checkNotFound(voteRepository.getLast(userId),"It seems you haven't voted yet.");
        return voteMapper.transformEntityIntoViewTo(vote);
    }

    @Transactional
    public VoteViewTo get(int userId, int id){
        log.debug("get userId={} id={}", userId,id);
        Vote vote = checkNotFound(voteRepository.get(userId,id), "You don't have such vote, or it doesn't belong to you.");
        return voteMapper.transformEntityIntoViewTo(vote);
    }
}
