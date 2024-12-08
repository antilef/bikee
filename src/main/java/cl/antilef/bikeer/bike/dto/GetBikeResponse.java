package cl.antilef.bikeer.bike.dto;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.common.StatusResult;

public record GetBikeResponse(Bike bike, StatusResult status, String message) {
}
