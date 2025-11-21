package com.example.automarketplace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;

    @Min(value = 1886, message = "Рік не може бути менше 1886")
    private int year;

    @DecimalMin(value = "0.0", inclusive = true, message = "Ціна не може бути від’ємною")
    private double price;

    @Min(value = 0, message = "Пробіг не може бути від’ємним")
    private int mileage;

    private String city;
    private String image;
    private String type; // new, used, parts

    @ManyToOne
    private User owner;

    // Геттери та сеттери
}
