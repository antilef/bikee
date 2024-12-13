package cl.antilef.bikeer.bike.entity;


import cl.antilef.bikeer.rent.entity.Rent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Id;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bikes")
public class Bike {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String serial;

    String brand;

    String model;

    String color;

    String size;

    BikeType Type;

    @ManyToMany
    @JoinTable(name = "bike_rent",
            joinColumns = @JoinColumn(name = "bike_id"),
            inverseJoinColumns = @JoinColumn(name = "rent_id"))
    @ToString.Exclude
    @JsonManagedReference
    @JsonIgnore
    List<Rent> rents ;

    public List<Integer> getRentsIds(){
        return this.rents.stream().map(Rent::getId).toList();
    }

    boolean available;

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", Type=" + Type +
                ", rents=" + getRentsIds() +
                ", available=" + available +
                '}';
    }
}
