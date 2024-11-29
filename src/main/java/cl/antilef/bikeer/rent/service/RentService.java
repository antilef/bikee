package cl.antilef.bikeer.rent.service;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.NoBikesFoundException;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import cl.antilef.bikeer.rent.dto.CreateRentRequestDTO;
import cl.antilef.bikeer.rent.dto.CreateRentResponseDTO;
import cl.antilef.bikeer.rent.entity.Rent;
import cl.antilef.bikeer.rent.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public CreateRentResponseDTO create(CreateRentRequestDTO request) throws Exception {

        List<Bike> bikes = bikeRepository.findAllIn(request.getBikes());

        if(bikes.isEmpty()){
            throw new NoBikesFoundException("No bikes founded");
        }

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

        rent = rentRepository.save(rent);

        for (Bike bike: bikes){
            List<String> rents = bike.getRents();
            if(rents == null){
                rents = new ArrayList<>();
            }
            rents.add(rent.getId());
            bike.setRents(rents);
        }

        bikeRepository.saveAll(bikes);

        return new CreateRentResponseDTO(
                rent,bikes

        );


    }
}
