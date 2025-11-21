package com.example.automarketplace.service;

import com.example.automarketplace.model.Car;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars = new ArrayList<>(); // Твої авто

    public List<Car> findAll() {
        return cars;
    }

    public List<Car> filter(Map<String, String> params) {
        Stream<Car> stream = cars.stream();

        if (params.containsKey("brand")) {
            String brand = params.get("brand").toLowerCase();
            stream = stream.filter(c -> c.getBrand().toLowerCase().contains(brand));
        }
        if (params.containsKey("model")) {
            String model = params.get("model").toLowerCase();
            stream = stream.filter(c -> c.getModel().toLowerCase().contains(model));
        }
        if (params.containsKey("yearMin")) {
            int yearMin = Integer.parseInt(params.get("yearMin"));
            stream = stream.filter(c -> c.getYear() >= yearMin);
        }
        if (params.containsKey("yearMax")) {
            int yearMax = Integer.parseInt(params.get("yearMax"));
            stream = stream.filter(c -> c.getYear() <= yearMax);
        }
        if (params.containsKey("priceMax")) {
            double priceMax = Double.parseDouble(params.get("priceMax"));
            stream = stream.filter(c -> c.getPrice() <= priceMax);
        }
        if (params.containsKey("mileageMax")) {
            int mileageMax = Integer.parseInt(params.get("mileageMax"));
            stream = stream.filter(c -> c.getMileage() <= mileageMax);
        }
        if (params.containsKey("city")) {
            String city = params.get("city").toLowerCase();
            stream = stream.filter(c -> c.getCity().toLowerCase().contains(city));
        }

        List<Car> filtered = stream.collect(Collectors.toList());

        // Сортування
        String sortBy = params.getOrDefault("sortBy", "");
        switch (sortBy) {
            case "priceAsc": filtered.sort(Comparator.comparing(Car::getPrice)); break;
            case "priceDesc": filtered.sort(Comparator.comparing(Car::getPrice).reversed()); break;
            case "yearAsc": filtered.sort(Comparator.comparing(Car::getYear)); break;
            case "yearDesc": filtered.sort(Comparator.comparing(Car::getYear).reversed()); break;
            case "mileageAsc": filtered.sort(Comparator.comparing(Car::getMileage)); break;
            case "mileageDesc": filtered.sort(Comparator.comparing(Car::getMileage).reversed()); break;
        }

        return filtered;
    }
}
