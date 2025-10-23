package ru.artem.Online.Market.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private boolean admin = false;
    private int balance = 1000;
    private List<Car> rented = new ArrayList<>();

    public void addFunds(int sum) {
        balance += sum;
    }

    public void rent(Car newCar, int days) {
        addRented(newCar);
        double sum = Math.round((newCar.getPrice() * days) * 10.0) / 10.0;
        if (balance >= sum) {
            balance -= sum;
        } else {

        }
    }

    public void addRented(Car car) {
        rented.add(car);
    }
}
