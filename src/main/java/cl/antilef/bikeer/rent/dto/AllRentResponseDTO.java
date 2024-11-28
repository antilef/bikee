package cl.antilef.bikeer.rent.dto;


import cl.antilef.bikeer.rent.entity.Rent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class AllRentResponseDTO {

    String userId;
    List<Rent> rents;

}
