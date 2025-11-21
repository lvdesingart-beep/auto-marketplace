package com.example.automarketplace.controller;

import com.example.automarketplace.model.Car;
import com.example.automarketplace.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.findAll().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null));
        return "car-detail";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        Car car = id == null ? new Car() : carService.findAll().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(new Car());
        model.addAttribute("car", car);
        return "car-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Car car) {
        if (car.getId() == null) {
            long next = carService.findAll().stream().mapToLong(c -> c.getId()).max().orElse(0L) + 1;
            car.setId(next);
            carService.add(car);
        } else {
            // naive replace: remove old and add new
            carService.findAll().removeIf(c -> c.getId().equals(car.getId()));
            carService.add(car);
        }
        return "redirect:/car/" + car.getId();
    }
}
