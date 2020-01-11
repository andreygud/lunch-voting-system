package com.gudilov.lunchvotingsystem.restaurant.to;

import com.gudilov.lunchvotingsystem.common.to.BaseTo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class MenuItemViewTo extends BaseTo {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer restaurantId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private double price;

    @NotNull
    private LocalDate menuDate;

    public MenuItemViewTo() {
    }

    public MenuItemViewTo(Integer id, Integer restaurantId, String name, double price, LocalDate menuDate) {
        super(id);
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.menuDate = menuDate;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDate menuDate) {
        this.menuDate = menuDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MenuItemViewTo that = (MenuItemViewTo) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (restaurantId != null ? !restaurantId.equals(that.restaurantId) : that.restaurantId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return menuDate != null ? menuDate.equals(that.menuDate) : that.menuDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (restaurantId != null ? restaurantId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (menuDate != null ? menuDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuItemTo{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", menuDate=" + menuDate +
                ", id=" + id +
                '}';
    }
}
