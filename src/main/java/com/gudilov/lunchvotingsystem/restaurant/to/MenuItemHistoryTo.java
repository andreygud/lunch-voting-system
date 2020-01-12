package com.gudilov.lunchvotingsystem.restaurant.to;

import java.time.LocalDateTime;

public class MenuItemHistoryTo {
    private static final long serialVersionUID = 1L;

    private LocalDateTime menuDate;

    private int restaurantId;
    private String restaurantName;

    private String dishName;
    private double price;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDateTime getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDateTime menuDate) {
        this.menuDate = menuDate;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItemHistoryTo that = (MenuItemHistoryTo) o;

        if (restaurantId != that.restaurantId) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (menuDate != null ? !menuDate.equals(that.menuDate) : that.menuDate != null) return false;
        if (restaurantName != null ? !restaurantName.equals(that.restaurantName) : that.restaurantName != null)
            return false;
        return dishName != null ? dishName.equals(that.dishName) : that.dishName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = menuDate != null ? menuDate.hashCode() : 0;
        result = 31 * result + restaurantId;
        result = 31 * result + (restaurantName != null ? restaurantName.hashCode() : 0);
        result = 31 * result + (dishName != null ? dishName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "MenuItemHistoryTo{" +
                "menuDate=" + menuDate +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                '}';
    }
}
