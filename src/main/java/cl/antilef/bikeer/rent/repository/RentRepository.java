package cl.antilef.bikeer.rent.repository;

import cl.antilef.bikeer.rent.entity.Rent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends MongoRepository<Rent,String> {
    public List<Rent> findByUserId(String userId);
}
