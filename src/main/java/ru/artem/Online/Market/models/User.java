package ru.artem.Online.Market.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс который содержит информацию о пользователе
 */
@Component
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private boolean admin = false;
    private int balance = 1000;
    private List<Car> rented = new ArrayList<>();

    /**
     * Метод пополняет баланс пользователя
     *
     * @param sum сумма денег
     */
    public void addFunds(int sum) {
        balance += sum;
    }

    /**
     * Метод осуществляет аренду машины
     *
     * @param newCar информация о машине
     * @param days   срок аренды в днях
     */
    public void rent(Car newCar, int days) {
        addRented(newCar);
        double sum = Math.round((newCar.getPrice() * days) * 10.0) / 10.0;
        if (balance >= sum) {
            balance -= sum;
        }
    }

    /**
     * Метод добавляет машину в список арендованных машин пользователя
     *
     * @param car данные о машине
     */
    public void addRented(Car car) {
        rented.add(car);
    }
}
