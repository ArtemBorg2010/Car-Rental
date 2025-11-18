package ru.artem.Online.Market.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.Garage;
import ru.artem.Online.Market.services.GarageService;
import ru.artem.Online.Market.services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация функционала гаража
 */
@Service
public class GarageServiceImpl implements GarageService {
    @Autowired
    private Garage garage;

    @Autowired
    private Car rentedCar;

    @Autowired
    private UserService userService;

    @Override
    public Garage getGarage() {
        return garage;
    }

    @Override
    public void addCar(Car car) {
        garage.addCar(car);
    }

    @Override
    public void removeCar(Car car) {
        garage.removeCar(car);
    }

    @Override
    public Car findCarValue(Car car) {
        boolean flag = false;
        for (Car value : garage.getCars()) {
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel())) {
                return value;
            }
        }
        Car empty = new Car();
        empty.setBrand("");
        empty.setModel("");
        empty.setYear(0);
        empty.setRun(0);
        return empty;
    }

    @Override
    public List<Car> getFoundCars(Car car) {
        List<Car> goodCars = new ArrayList<>();
        for (Car value : garage.getCars()) {
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel())) {
                goodCars.add(value);
            }
        }
        return goodCars;
    }

    @Override
    public boolean findCar(Car car) {
        boolean flag = false;
        for (Car value : garage.getCars()) {
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<Car> rentACar(Car newCar, int time) {
        if (findCar(newCar)) {
            rentedCar = findCarValue(newCar);
            List<Car> goodCars = getFoundCars(newCar);
            userService.rent(rentedCar, time);
            return goodCars;
        } else {
            return new ArrayList<>();
        }
    }
}
