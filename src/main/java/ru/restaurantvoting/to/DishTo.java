package ru.restaurantvoting.to;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DishTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    @Range(min = 1)
    private Integer price;

    public DishTo() {
    }

    public DishTo(Integer id, String name, Integer price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                ", id=" + id +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}