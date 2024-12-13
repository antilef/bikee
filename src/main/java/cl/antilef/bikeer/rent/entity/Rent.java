package cl.antilef.bikeer.rent.entity;



import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    boolean activate;

    private String price;

    @ManyToMany(mappedBy = "rents")
    private List<Bike> bikes;

    public boolean isDeactivate(){
        return !this.activate;
    }

    public Iterable<Integer> getBikeIds(){
        return bikes.stream().map(Bike::getId).toList();
    }

    public void setAvailableAllBikes(Iterable<Bike> bikes) {
        bikes.forEach(bike -> {
            bike.setAvailable(true);
        });
    }
}
