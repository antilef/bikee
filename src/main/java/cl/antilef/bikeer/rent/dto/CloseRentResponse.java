package cl.antilef.bikeer.rent.dto;

import cl.antilef.bikeer.common.StatusResult;

public record CloseRentResponse(String status, StatusResult result,String message) {
}
