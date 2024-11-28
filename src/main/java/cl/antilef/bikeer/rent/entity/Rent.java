package cl.antilef.bikeer.rent.entity;


import cl.antilef.bikeer.bike.entity.Bike;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

public class Rent {

    @Id
    private String id;

    LocalDateTime startDate;

    LocalDateTime endDate;

    boolean activate;

    String price;

    Payment payment;

    List<String> bikes;

}
