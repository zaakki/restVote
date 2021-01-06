package ru.restaurantvoting.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class DishTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    @Range(min = 1)
    private Integer price;



    public DishTo(Integer id, String name, Integer price) {
        super(id);
        this.name = name;
        this.price = price;
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