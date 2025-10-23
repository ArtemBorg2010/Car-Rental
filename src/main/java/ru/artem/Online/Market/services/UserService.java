package ru.artem.Online.Market.services;

import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.User;

public interface UserService {
    User fillUser(User newUser);

    void rent(Car newCar, int days);
    User getUser();
    void setUser(User user);
}
