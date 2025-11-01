package ru.artem.Online.Market.services;

import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.Garage;
import ru.artem.Online.Market.models.User;

/**
 * Интерфейс, описывающий функционал пользователя
 */

public interface UserService {
    /**
     * Метод заполняет информацию о пользователе
     *
     * @param newUser данные авторизации
     * @return пользователь
     */
    User fillUser(User newUser);

    /**
     * Метод берет в аренду машину
     *
     * @param newCar информация о машине
     * @param days   срок аренды в днях
     */
    void rent(Car newCar, int days);

    /**
     * Метод возвращает информацию о пользователе
     *
     * @return информацию о пользователе в виде {@link User}
     */
    User getUser();

    /**
     * Метод изменяет информацию о пользователе
     *
     * @param user пользователь с обновленными данными
     */
    void setUser(User user);
}
