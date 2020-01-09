package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.utils.SecurityUtil;
import com.gudilov.lunchvotingsystem.restaurant.service.ReportingService;
import com.gudilov.lunchvotingsystem.restaurant.service.VoteService;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;
import com.gudilov.lunchvotingsystem.restaurant.to.VotingResultTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/vote";

    VoteService voteService;
    ReportingService reportingService;

    public VoteRestController(VoteService voteService, ReportingService reportingService) {
        this.voteService = voteService;
        this.reportingService = reportingService;
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
    public VoteViewTo get(@PathVariable int id) {
        int userId = SecurityUtil.authorizedUser();
        log.debug("rest get userId={}, id={}", userId, id);

        return voteService.get(userId, id);
    }

    @GetMapping("/result")
    public List<VotingResultTo> votingResult(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.debug("rest votingResult date={}", date);
        if (date == null) {
            date = LocalDate.now();
        }
        return reportingService.votingResult(date);
    }
}


