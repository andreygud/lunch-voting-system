package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.utils.SecurityUtil;
import com.gudilov.lunchvotingsystem.restaurant.service.VoteService;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.debug("rest vote restaurantId={}", restaurantId);
        int userId = SecurityUtil.authorizedUser();
        return voteService.vote(userId, restaurantId);
    }
}


