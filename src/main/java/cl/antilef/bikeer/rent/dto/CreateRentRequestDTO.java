package cl.antilef.bikeer.rent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CreateRentRequestDTO {

    private String userId;
    private LocalDateTime endDate;
    private String price;
    private List<String> bikes;

}
