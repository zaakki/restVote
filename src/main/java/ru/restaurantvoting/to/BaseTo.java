package ru.restaurantvoting.to;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.restaurantvoting.HasId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseTo implements HasId {
    protected Integer id;

}
