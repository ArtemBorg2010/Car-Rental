package ru.artem.Online.Market.services;

import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.Garage;

import java.util.List;


public interface GarageService {
    Garage getGarage();
    void addCar(Car car);

    void removeCar(Car car);

    Car findCarValue(Car car);

    List<Car> getFoundCars(Car car);

    boolean findCar(Car car);

    List<Car> rentACar(Car newCar,
                     int time);
}
