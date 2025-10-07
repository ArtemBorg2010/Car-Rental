package ru.artem.Online.Market.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Component
public class Garage {
    private List<Car> cars = new ArrayList<>();

    public Garage() {
        Car car1 = new Car();

        car1.setBrand("Mercedes-Benz");
        car1.setModel("Maybach S680");
        car1.setYear(2025);
        car1.setRun(20);
        car1.setPrice(999);

        Car car2 = new Car();

        car2.setBrand("Lamborghini");
        car2.setModel("Urus SE");
        car2.setYear(2025);
        car2.setRun(1120);
        car2.setPrice(599);

        Car car3 = new Car();

        car3.setBrand("Cadillac");
        car3.setModel("Escalade");
        car3.setYear(2023);
        car3.setRun(2479);
        car3.setPrice(299);

        cars.addAll(Arrays.asList(car1, car2, car3));
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            Car value = cars.get(i);
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel()) &&
                    car.getYear() == value.getYear() &&
                    car.getRun() == value.getRun()) {
                cars.remove(i);
                break;
            }
        }
    }

    public boolean findCar(Car car) {
        boolean flag = false;
        for (Car value : cars) {
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public Car findCarValue(Car car) {
        boolean flag = false;
        for (Car value : cars) {
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

    public List<Car> getFoundCars(Car car) {
        List<Car> goodCars = new ArrayList<>();
        for (Car value : cars) {
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel())) {
                goodCars.add(value);
            }
        }
        return goodCars;
    }
}
