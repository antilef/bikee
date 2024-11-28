package cl.antilef.bikeer.bike.entity;


import org.springframework.data.annotation.Id;

import java.util.List;

public class Bike {

    @Id
    String id;

    String serial;

    String brand;

    String model;

    String color;

    String size;

    BikeType Type;

    List<String> rents;

}
