package cl.antilef.bikeer.bike.dto;

import cl.antilef.bikeer.bike.entity.Bike;

import java.util.List;

public record GetAvailableBikes(List<Bike> bikes, int quantity) {
}
