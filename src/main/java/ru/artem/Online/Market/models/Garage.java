package ru.artem.Online.Market.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class Garage {
    private List<Car> cars;

    public void fill() {
        Car car1 = new Car();

        car1.setBrand("Mercedes-Benz");
        car1.setModel("Maybach S680");
        car1.setYear(2025);
        car1.setRun(20);

        Car car2 = new Car();

        car2.setBrand("Lamborghini");
        car2.setModel("Urus SE");
        car2.setYear(2025);
        car2.setRun(1120);

        Car car3 = new Car();

        car3.setBrand("Cadillac");
        car3.setModel("Escalade");
        car3.setYear(2023);
        car3.setRun(2479);


        cars = List.of(car1, car2, car3);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public boolean findCar(Car car) {
        boolean flag = false;
        for (Car value : cars) {
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel())) {
                flag=true;
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
        Car empty=new Car();
        empty.setBrand("");
        empty.setModel("");
        empty.setYear(0);
        empty.setRun(0);
        return empty;
    }

    public List<Car> getFoundCars(Car car){
        List<Car>goodCars=new ArrayList<>();
        for (Car value : cars) {
            if (car.getBrand().equals(value.getBrand()) &&
                    car.getModel().equals(value.getModel())) {
                goodCars.add(value);
            }
        }
        return goodCars;
    }
}
