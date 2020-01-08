package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.utils.SecurityUtil;
import com.gudilov.lunchvotingsystem.restaurant.service.VoteService;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/vote";

    VoteService voteService;

    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{restaurantId}")
    public VoteViewTo vote(@PathVariable int restaurantId) {
        int userId = SecurityUtil.authorizedUser();
        log.debug("rest vote restaurantId={} userId={}", restaurantId,userId);

        return voteService.vote(userId, restaurantId);
    }

    @GetMapping("/last")
    public VoteViewTo lastVote(){
        int userId = SecurityUtil.authorizedUser();
        log.debug("rest lastVote userId={}", userId);

        return voteService.getLast(userId);
    }

    @GetMapping("/{id}")
    public VoteViewTo get(@PathVariable int id){
        int userId = SecurityUtil.authorizedUser();
        log.debug("rest get userId={}, id={}", userId, id);

        return voteService.get(userId,id);
    }
}


