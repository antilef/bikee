package cl.antilef.bikeer.bike.dto;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.common.StatusResult;

import java.util.List;

public record GetAllBikeByRentResponse(List<Bike> bikes, StatusResult result, String message, int length) {
}
