package cl.antilef.bikeer.bike.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
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
