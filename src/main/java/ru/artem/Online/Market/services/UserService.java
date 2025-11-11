package ru.artem.Online.Market.services;

import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.User;

/**
 * Интерфейс, описывающий функционал пользователя
 */
public interface UserService {
    /**
     * Метод заполняет информацию о пользователе
     *
     * @param newUser данные авторизации
     * @return {@link User}
     */
    User fillUser(User newUser);

    /**
     * Метод осуществляет аренду машины
     *
     * @param newCar информация о машине
     * @param days   срок аренды в днях
     */
    void rent(Car newCar, int days);

    /**
     * Метод возвращает информацию о пользователе
     *
     * @return {@link User}
     */
    User getUser();

    /**
     * Метод изменяет информацию о пользователе
     *
     * @param user обновленные данные пользователя
     */
    void setUser(User user);
}
