package cl.antilef.bikeer.rent.service;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import cl.antilef.bikeer.rent.dto.CreateRentRequestDTO;
import cl.antilef.bikeer.rent.entity.Rent;
import cl.antilef.bikeer.rent.repository.RentRepository;
import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.exceptions.UserNotFoundException;
import cl.antilef.bikeer.user.repository.MongoUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final BikeRepository bikeRepository;

    public RentService(RentRepository repository, BikeRepository bikeRepository) {

        this.rentRepository = repository;
        this.bikeRepository = bikeRepository;

    }

    public List<Rent> getAll(String userId) {

        return rentRepository.findByUserId(userId);

    }

    public Rent create(CreateRentRequestDTO request){

        List<Bike> bikes = bikeRepository.findAllIn(request.getBikes());

        Rent rent = Rent
                .builder()
                .price(request.getPrice())
                .bikes(request.getBikes())
                .activate(true)
                .startDate(LocalDateTime.now())
                .endDate(request.getEndDate())
                .userId(request.getUserId())
                .payment(null)
                .build();


        for (Bike bike:bikes){
            List<String> rents = bike.getRents();
            rents.add(rent.getId());
            bike.setRents(rents);
            bikeRepository.save(bike);
        }

        return rentRepository.save(rent);


    }
}
