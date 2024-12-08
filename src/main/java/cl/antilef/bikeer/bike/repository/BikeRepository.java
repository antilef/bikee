package cl.antilef.bikeer.bike.repository;

import cl.antilef.bikeer.bike.entity.Bike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BikeRepository extends MongoRepository<Bike,String> {

    @Query(value="{'_id': {$in: ?0}}")
    List<Bike> findAllIn(List<String> ids);

    @Query(value="{'rents': {$in : ?0}}")
    List<Bike> findAllByRent(List<String> id);
}
