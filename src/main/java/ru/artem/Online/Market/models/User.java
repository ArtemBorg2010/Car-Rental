package ru.artem.Online.Market.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private boolean admin=false;
    private int balance;
    private List<Car> rented;

    public void rent(Car newCar){
        rented.add(newCar);
    }
}
