package cl.antilef.bikeer.rent.service;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.NoBikesFoundException;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import cl.antilef.bikeer.common.StatusResult;
import cl.antilef.bikeer.common.WebConstant;
import cl.antilef.bikeer.rent.dto.CloseRentRequest;
import cl.antilef.bikeer.rent.dto.CloseRentResponse;
import cl.antilef.bikeer.rent.dto.CreateRentRequest;
import cl.antilef.bikeer.rent.dto.CreateRentResponse;
import cl.antilef.bikeer.rent.entity.Rent;
import cl.antilef.bikeer.rent.exception.AlreadyDeactivateRentException;
import cl.antilef.bikeer.rent.exception.RentNotExistException;
import cl.antilef.bikeer.rent.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public CreateRentResponse create(CreateRentRequest request) throws Exception {

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
            bike.setAvailable(false);
        }

        bikeRepository.saveAll(bikes);

        return new CreateRentResponse(
                rent,bikes

        );


    }

    public CloseRentResponse closeRent(CloseRentRequest request) throws AlreadyDeactivateRentException, RentNotExistException {


        Rent rent = findValidClosableRent(request.idRent());


        rent.setActivate(false);
        for(String bikeId: rent.getBikes()){
            Optional<Bike> bikeOptional = bikeRepository.findById(bikeId);
            if(bikeOptional.isPresent()){
                Bike bike = bikeOptional.get();
                bike.setAvailable(true);
                bikeRepository.save(bike);
            }

        }

        rentRepository.save(rent);

        return new CloseRentResponse(
                WebConstant.SUCCESS_TEXT, StatusResult.OK,"The rent was closed successfully"
        );
    }

    private Rent findValidClosableRent(String id) throws RentNotExistException, AlreadyDeactivateRentException {

        Optional<Rent> optionalRent = rentRepository.findById(id);

        if(optionalRent.isEmpty() || optionalRent.get().getId() == null){
            throw new RentNotExistException("The rent not exist");
        }

        Rent rent = optionalRent.get();

        if(rent.isDeactivate()) {
            throw new AlreadyDeactivateRentException("The rent already was deactivate");
        }
        return rent;
    }
}
