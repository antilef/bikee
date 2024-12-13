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
import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.exceptions.UserNotFoundException;
import cl.antilef.bikeer.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final BikeRepository bikeRepository;
    private final UserRepository userRepository;

    public RentService(RentRepository repository, BikeRepository bikeRepository, UserRepository userRepository) {

        this.rentRepository = repository;
        this.bikeRepository = bikeRepository;
        this.userRepository = userRepository;

    }

    public List<Rent> getAll(String userId) {

        return rentRepository.findByUserId(Integer.parseInt(userId));

    }

    public CreateRentResponse create(CreateRentRequest request) throws Exception {

        //List<Bike> bikes = bikeRepository.findAllIn(request.getBikes());
        List<Bike> bikes = new ArrayList<>();

        bikeRepository.findAllById(
                request.getBikes()
                        .stream()
                        .map(Integer::parseInt)
                        .toList()
        ).forEach(bikes::add);

        if(!bikes.iterator().hasNext()){
            throw new NoBikesFoundException("No bikes founded");
        }

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(request.getUserId()));
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("The user was not founded");
        }

        User user = optionalUser.get();

        Rent rent = Rent
                .builder()
                .price(request.getPrice())
                .bikes(bikes)
                .activate(true)
                .startDate(LocalDateTime.now())
                .endDate(request.getEndDate())
                .user(user)
                //.payment(null)
                .build();

        rent = rentRepository.save(rent);

        for (Bike bike: bikes){
            List<Rent> rents = bike.getRents();
            if(rents == null){
                rents = new ArrayList<>();
            }
            rents.add(rent);
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
        Iterable<Bike> bikes = bikeRepository.findAllById(rent.getBikeIds());


        rent.setActivate(false);
        rent.setAvailableAllBikes(bikes);


        bikeRepository.saveAll(bikes);
        rentRepository.save(rent);

        return new CloseRentResponse(
                WebConstant.SUCCESS_TEXT, StatusResult.OK,"The rent was closed successfully"
        );
    }

    private Rent findValidClosableRent(String id) throws RentNotExistException, AlreadyDeactivateRentException {

        Optional<Rent> optionalRent = rentRepository.findById(Integer.parseInt(id));

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
