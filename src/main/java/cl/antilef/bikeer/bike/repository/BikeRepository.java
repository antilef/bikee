package cl.antilef.bikeer.bike.repository;

import cl.antilef.bikeer.bike.entity.Bike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BikeRepository extends CrudRepository<Bike,Integer> {
    @Query("SELECT b FROM Bike b JOIN b.rents r WHERE r.id = :rentId")
    List<Bike> findAllByRent(@Param("rentId") Integer rentId);

    List<Bike> findByAvailable(boolean available);
}
