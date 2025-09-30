package ru.artem.Online.Market.services;

import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.User;

import java.util.ArrayList;

public interface UserService {
    User fillUser(User newUser);

    void rent(Car newCar, int days);
    void setAdmin(boolean admin);
}
