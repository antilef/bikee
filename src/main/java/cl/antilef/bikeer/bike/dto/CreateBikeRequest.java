package cl.antilef.bikeer.bike.dto;

import cl.antilef.bikeer.bike.entity.BikeType;
public record CreateBikeRequest(
        String serial,
        String brand,
        String model,
        String color,
        String size,
        BikeType Type){
}
