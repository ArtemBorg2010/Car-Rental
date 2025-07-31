package ru.artem.Online.Market.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Car {
    private String brand;
    private String model;
    private int year;
    private int run;
}
