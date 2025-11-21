package com.example.automarketplace.controller;

import com.example.automarketplace.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(required = false) String q,
                        @RequestParam(required = false) String make,
                        @RequestParam(required = false) Integer minYear,
                        @RequestParam(required = false) Integer maxYear,
                        @RequestParam(required = false) Integer minPrice,
                        @RequestParam(required = false) Integer maxPrice,
                        @RequestParam(required = false) Integer minMileage,
                        @RequestParam(required = false) Integer maxMileage,
                        Model model) {
        model.addAttribute("cars", carService.search(q, make, minYear, maxYear, minPrice, maxPrice, minMileage, maxMileage));
        model.addAttribute("query", q == null ? "" : q);
        model.addAttribute("make", make == null ? "" : make);
        model.addAttribute("minYear", minYear == null ? "" : minYear);
        model.addAttribute("maxYear", maxYear == null ? "" : maxYear);
        model.addAttribute("minPrice", minPrice == null ? "" : minPrice);
        model.addAttribute("maxPrice", maxPrice == null ? "" : maxPrice);
        model.addAttribute("minMileage", minMileage == null ? "" : minMileage);
        model.addAttribute("maxMileage", maxMileage == null ? "" : maxMileage);
        model.addAttribute("makes", java.util.Arrays.asList("Toyota","Ford","Renault","Chevrolet"));
        return "index";
    }
}
