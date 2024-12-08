package cl.antilef.bikeer.rent.dto;

import java.util.List;

public record CloseRentRequest(String idRent, List<String> bikes) {
}
