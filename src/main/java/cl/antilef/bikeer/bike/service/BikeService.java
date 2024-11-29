package cl.antilef.bikeer.bike.service;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.BikeNotFoundException;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikeService {

    private BikeRepository bikeRepository;

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
}
