package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.web.security.SecurityUtil;
import com.gudilov.lunchvotingsystem.restaurant.reports.ReportingRepository;
import com.gudilov.lunchvotingsystem.restaurant.service.VoteService;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;
import com.gudilov.lunchvotingsystem.restaurant.to.VotingHistoryTo;
import com.gudilov.lunchvotingsystem.restaurant.to.VotingResultTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.gudilov.lunchvotingsystem.restaurant.web.RestaurantRestController.DEFAULT_HISTORY_DEPTH;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/vote";

    VoteService voteService;
    ReportingRepository reportingRepository;

    public VoteRestController(VoteService voteService, ReportingRepository reportingRepository) {
        this.voteService = voteService;
        this.reportingRepository = reportingRepository;
    }

    @PostMapping("")
    public VoteViewTo vote(@RequestParam int restaurantId) {
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
        return reportingRepository.getVotingResults(date);
    }

    @GetMapping("/history")
    public List<VotingHistoryTo> votingHistory(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start) {
        log.debug("rest votinghistory start={}", start);
        int userId = SecurityUtil.authorizedUser();
        if (start == null) {
            start = LocalDate.now().minusDays(DEFAULT_HISTORY_DEPTH);
        }
        return reportingRepository.getVotingHistory(userId, start);
    }
}


