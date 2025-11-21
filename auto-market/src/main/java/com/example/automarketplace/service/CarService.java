package com.example.automarketplace.service;

import com.example.automarketplace.model.Car;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final List<Car> cars = new ArrayList<>();

    @PostConstruct
    public void init() {
        cars.add(new Car(1L, "Toyota", "Corolla", 2018, 18000, "/images/corolla.jpg", "Kyiv", 85000));
        cars.add(new Car(2L, "Ford", "Focus", 2017, 15000, "/images/focus.jpg", "Lviv", 120000));
        cars.add(new Car(3L, "Renault", "Symbol", 2015, 9000, "/images/symbol.jpg", "Odesa", 145000));
        cars.add(new Car(4L, "Chevrolet", "Aveo", 2014, 7000, "/images/aveo.jpg", "Kharkiv", 160000));
    }

    public List<Car> findAll() {
        return cars;
    }

    public List<Car> search(String q, String make, Integer minYear, Integer maxYear,
                            Integer minPrice, Integer maxPrice, Integer minMileage, Integer maxMileage) {
        String term = (q == null) ? "" : q.trim().toLowerCase();
        return cars.stream()
                .filter(c -> term.isEmpty() || (c.getMake() + " " + c.getModel()).toLowerCase().contains(term))
                .filter(c -> (make == null || make.isEmpty()) || c.getMake().equalsIgnoreCase(make))
                .filter(c -> (minYear == null) || c.getYear() >= minYear)
                .filter(c -> (maxYear == null) || c.getYear() <= maxYear)
                .filter(c -> (minPrice == null) || c.getPrice() >= minPrice)
                .filter(c -> (maxPrice == null) || c.getPrice() <= maxPrice)
                .filter(c -> (minMileage == null) || (c.getMileage() != null && c.getMileage() >= minMileage))
                .filter(c -> (maxMileage == null) || (c.getMileage() != null && c.getMileage() <= maxMileage))
                .collect(Collectors.toList());
    }

    public void add(Car car) {
        cars.add(car);
    }
}
