package cl.antilef.bikeer.rent.entity;



import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Id;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    @ToString.Exclude
    @JsonManagedReference
    @JsonIgnore
    private List<Bike> bikes;

    public boolean isDeactivate(){
        return !this.activate;
    }

    public Iterable<Integer> getBikeIds(){
        if(bikes == null){
            return Collections.emptyList();
        }
        return bikes.stream().map(Bike::getId).toList();
    }

    public void setAvailableAllBikes(Iterable<Bike> bikes) {
        bikes.forEach(bike -> {
            bike.setAvailable(true);
        });
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", user=" + user +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", activate=" + activate +
                ", price='" + price + '\'' +
                ", bikes=" + getBikeIds() +
                '}';
    }
}
