package ru.artem.Online.Market.services.impl;

import org.springframework.stereotype.Service;
import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.User;
import ru.artem.Online.Market.services.UserService;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    private User user;

    @Override
    public User fillUser(User newUser) {
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setRented(new ArrayList<>());
        user.setBalance(1000);
        user.setAdmin(false);
        return user;
    }

    @Override
    public void rent(Car newCar, int days) {
        System.out.println(user.getUsername()+" "+user.getPassword());
        int balance = user.getBalance();
        user.addRented(newCar);
        double sum = Math.round((newCar.getPrice() * days) * 10.0) / 10.0;
        if (balance >= sum) {
            balance -= sum;
        }
        user.setBalance(balance);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User newUser) {
        user=newUser;
    }
}
