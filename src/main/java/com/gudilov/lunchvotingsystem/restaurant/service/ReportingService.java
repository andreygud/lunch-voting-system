package com.gudilov.lunchvotingsystem.restaurant.service;

import com.gudilov.lunchvotingsystem.restaurant.reports.ReportingRepository;
import com.gudilov.lunchvotingsystem.restaurant.to.VotingResultTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportingService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private ReportingRepository reportingRepository;

    public ReportingService(ReportingRepository reportingRepository) {
        this.reportingRepository = reportingRepository;
    }

    public List<VotingResultTo> votingResult(LocalDate day) {
        log.debug("votingResult day={}", day);
        return reportingRepository.getVotingResults(day);
    }
}
