package com.gudilov.lunchvotingsystem.restaurant;

import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;

import java.time.LocalDate;

public class MenuItemTestData {
    public static final MenuItemTo CACTUS_ITEM1_BURGER_UPDATE_TO = new MenuItemTo(100007, "Cactus Burger", 23L, LocalDate.now());
    public static final MenuItemViewTo CACTUS_ITEM1_BURGER_VIEW_TO = new MenuItemViewTo(100007, 100002, "Cactus Burget", 16, LocalDate.now());
    public static final MenuItemViewTo CACTUS_ITEM2_BURGER_VIEW_TO = new MenuItemViewTo(100008, 100002, "Quasadilias", 10.5, LocalDate.now());
    public static final MenuItemViewTo CACTUS_ITEM3_BURGER_VIEW_TO = new MenuItemViewTo(100009, 100002, "Rice Bowl", 10.5, LocalDate.now());
    public static final int CACTUS_ITEM1_BURGER_ID = 100007;
}
