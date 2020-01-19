package com.gudilov.lunchvotingsystem.restaurant;

import com.gudilov.lunchvotingsystem.common.TestMatchers;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;

import java.time.LocalDate;

public class MenuItemTestData {
    public static final MenuItemTo CACTUS_ITEM1_BURGER_UPDATE_TO = new MenuItemTo(100007, "Cactus Burger", 23);
    public static final MenuItemViewTo CACTUS_ITEM1_BURGER_UPDATE_VIEW_TO = new MenuItemViewTo(100007, 100002, "Cactus Burger", 23, LocalDate.now());
    public static final MenuItemViewTo CACTUS_ITEM1_BURGER_VIEW_TO = new MenuItemViewTo(100007, 100002, "Cactus Burget", 16, LocalDate.now());
    public static final MenuItemViewTo CACTUS_ITEM2_BURGER_VIEW_TO = new MenuItemViewTo(100008, 100002, "Quasadilias", 10.5, LocalDate.now());
    public static final MenuItemViewTo CACTUS_ITEM3_BURGER_VIEW_TO = new MenuItemViewTo(100009, 100002, "Rice Bowl", 10.5, LocalDate.now());
    public static final MenuItemTo CACTUS_ITEM_NEW_TO = new MenuItemTo(null, "Something new", 17.25);
    public static final MenuItemViewTo CACTUS_ITEM_NEW_VIEW_TO = new MenuItemViewTo(100026, 100002, "Something new", 17.25, LocalDate.now());
    public static final String HISTORY_JSON = "[{\"restaurantId\":100002,\"restaurantName\":\"Cactus Club Cafe Kingsway\",\"dishName\":\"Cactus Burget\",\"price\":16.0},{\"restaurantId\":100002,\"restaurantName\":\"Cactus Club Cafe Kingsway\",\"dishName\":\"Quasadilias\",\"price\":10.5},{\"restaurantId\":100002,\"restaurantName\":\"Cactus Club Cafe Kingsway\",\"dishName\":\"Rice Bowl\",\"price\":10.5},{\"restaurantId\":100003,\"restaurantName\":\"Original Joe's Restaurant & Bar\",\"dishName\":\"Four mushroom steak\",\"price\":33.0},{\"restaurantId\":100003,\"restaurantName\":\"Original Joe's Restaurant & Bar\",\"dishName\":\"Joe Burger\",\"price\":17.0},{\"restaurantId\":100003,\"restaurantName\":\"Original Joe's Restaurant & Bar\",\"dishName\":\"Ribs\",\"price\":25.0},{\"restaurantId\":100003,\"restaurantName\":\"Original Joe's Restaurant & Bar\",\"dishName\":\"Yellow tail sushi\",\"price\":12.0},{\"restaurantId\":100004,\"restaurantName\":\"Hi Genki Restaurant\",\"dishName\":\"California roll\",\"price\":14.0}]";
    public static final String HISTORY_JSON_SINGLEREST = "[{\"restaurantId\":100002,\"restaurantName\":\"Cactus Club Cafe Kingsway\",\"dishName\":\"Cactus Burget\",\"price\":16.0},{\"restaurantId\":100002,\"restaurantName\":\"Cactus Club Cafe Kingsway\",\"dishName\":\"Quasadilias\",\"price\":10.5},{\"restaurantId\":100002,\"restaurantName\":\"Cactus Club Cafe Kingsway\",\"dishName\":\"Rice Bowl\",\"price\":10.5}]";

    public static final int CACTUS_ITEM1_BURGER_ID = 100007;

    public static final TestMatchers<MenuItemViewTo> ITEM_TO_TEST_MATCHERS = TestMatchers.useEquals(MenuItemViewTo.class);

}
