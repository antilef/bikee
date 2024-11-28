package cl.antilef.bikeer.rent.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder

public class Rent {

    @Id
    private String id;

    private String userId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    boolean activate;

    private String price;

    private Payment payment;

    private List<String> bikes;

}
