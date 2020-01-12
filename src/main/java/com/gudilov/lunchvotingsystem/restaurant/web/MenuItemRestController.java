package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.restaurant.reports.ReportingRepository;
import com.gudilov.lunchvotingsystem.restaurant.service.MenuItemService;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemHistoryTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(MenuItemRestController.REST_URL)
public class MenuItemRestController {
    public static final int DEFAULT_HISTORY_DEPTH = 14;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/menuItems/";

    private MenuItemService menuItemService;
    private ReportingRepository reportingRepository;

    public MenuItemRestController(MenuItemService menuItemService, ReportingRepository reportingRepository) {
        this.menuItemService = menuItemService;
        this.reportingRepository = reportingRepository;
    }

    @GetMapping("/{id}")
    public MenuItemViewTo get(@PathVariable int id) {
        log.debug("rest get menuitem id={}", id);
        return menuItemService.get(id);
    }

    @GetMapping("/history")
    public List<MenuItemHistoryTo> history(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start){
        log.debug("rest get menuitems history startdate={}", start);

        if(start == null){
            start = LocalDate.now().minusDays(DEFAULT_HISTORY_DEPTH);
        }
        return reportingRepository.getMenuHistory(start);
    }
}
