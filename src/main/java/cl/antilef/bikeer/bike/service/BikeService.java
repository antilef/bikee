package cl.antilef.bikeer.bike.service;

import cl.antilef.bikeer.bike.dto.CreateBikeRequest;
import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.BikeNotFoundException;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    private static final Logger logger = LoggerFactory.getLogger(BikeService.class);

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }




    public Bike getById(String id) {

        Optional<Bike> bikeOptional = bikeRepository.findById(Integer.parseInt(id));
        if(bikeOptional.isEmpty()){
            throw new BikeNotFoundException("Bike was not found");
        }

        return bikeOptional.get() ;

    }

    public List<Bike> getAllBikesByRent(String id) {
        logger.info("Calling BikeService[getAllBikes]");
        return bikeRepository.findAllByRent(Integer.parseInt(id));

    }

    public Bike createBike(CreateBikeRequest createBikeRequest){
        logger.info("Creating Bike...");
        Bike bike = Bike.builder()
                .serial(createBikeRequest.serial())
                .bikeType(createBikeRequest.Type())
                .model(createBikeRequest.model())
                .color(createBikeRequest.color())
                .size(createBikeRequest.size())
                .brand(createBikeRequest.brand())
                .rents(new ArrayList<>())
                .available(true)
                .build();
        return bikeRepository.save(bike);
    }

    public List<Bike> getAllBikesAvailables() {
        logger.info("Searching all bikes that are available...");
        System.out.println("Sascrhcj");
        return bikeRepository.findByAvailable(true);
    }
}
