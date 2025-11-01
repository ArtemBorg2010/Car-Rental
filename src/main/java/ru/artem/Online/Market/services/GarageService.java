package ru.artem.Online.Market.services;

import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.Garage;

import java.util.List;

/**
 * Интерфейс, описывающий функционал гаража
 */

public interface GarageService {
    /**
     * Метод возвращает информацию о гараже
     *
     * @return информацию о гараже в виде {@link Garage}
     */
    Garage getGarage();

    /**
     * Метод добавляет машину в гараж
     *
     * @param car данные о машине
     */
    void addCar(Car car);

    /**
     * Метод удаляет машину из гаража
     *
     * @param car данные о машине
     */
    void removeCar(Car car);

    /**
     * Метод для получения данных о найденной машине
     *
     * @param car данные о машине
     * @return данные о машине
     */
    Car findCarValue(Car car);

    /**
     * Метод для получения списка машин, удовлетворяющих требованиям пользователя для аренды автомобиля
     *
     * @param car данные о машине
     * @return список машин
     */
    List<Car> getFoundCars(Car car);

    /**
     * Метод находит машину в гараже
     *
     * @param car данные о машине
     * @return Да или нет
     */
    boolean findCar(Car car);

    /**
     * Метод берет в аренду машину
     *
     * @param newCar информация о машине
     * @param time   срок аренды в днях
     */
    List<Car> rentACar(Car newCar,
                       int time);
}
