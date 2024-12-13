package cl.antilef.bikeer.rent.repository;

import cl.antilef.bikeer.rent.entity.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentRepository extends CrudRepository<Rent,Integer> {

    @Query("SELECT r FROM Rent r WHERE r.user.id = :userId")
    List<Rent> findByUserId(@Param("userId") Integer userId);
}
