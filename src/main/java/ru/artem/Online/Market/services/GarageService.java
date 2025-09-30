package ru.artem.Online.Market.services;

import ru.artem.Online.Market.models.Car;

import java.util.ArrayList;
import java.util.List;

public interface GarageService {
    void addCar(Car car);
    void removeCar(Car car);
    Car findCarValue(Car car);
    List<Car> getFoundCars(Car car);
    boolean findCar(Car car);
}
