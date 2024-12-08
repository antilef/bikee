package cl.antilef.bikeer.bike.service;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.BikeNotFoundException;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import cl.antilef.bikeer.config.JwtAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    private static final Logger logger = LoggerFactory.getLogger(BikeService.class);

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }




    public Bike getById(String id) throws BikeNotFoundException {

        Optional<Bike> bikeOptional = bikeRepository.findById(id);
        if(bikeOptional.isEmpty()){
            throw new BikeNotFoundException("Bike was not found");
        }

        return bikeOptional.get() ;

    }

    public List<Bike> getAllBikes(String id) {
        logger.info("Calling BikeService[getAllBikes]");
        return bikeRepository.findAllByRent(List.of(id));

    }
}
