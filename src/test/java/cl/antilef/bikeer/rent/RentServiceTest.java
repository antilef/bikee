package cl.antilef.bikeer.rent;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.entity.BikeType;
import cl.antilef.bikeer.bike.exception.NoBikesFoundException;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import cl.antilef.bikeer.rent.dto.CreateRentRequest;
import cl.antilef.bikeer.rent.dto.CreateRentResponse;
import cl.antilef.bikeer.rent.entity.Rent;
import cl.antilef.bikeer.rent.repository.RentRepository;
import cl.antilef.bikeer.rent.service.RentService;
import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class RentServiceTest {

    private RentService rentService;
    private RentRepository rentRepository;
    private BikeRepository bikeRepository;
    private UserRepository userRepository;

    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);


    @BeforeEach
    void setUp(){
        bikeRepository = Mockito.mock(BikeRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        rentRepository = Mockito.mock(RentRepository.class);
        rentService = new RentService(rentRepository,bikeRepository,userRepository);
    }


    @Test
    void testCreateRent() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        User user = new User("francisco","antilef","antilef","536254263","wfsdfsadfsfd");
        Bike bike = new Bike(1,"32BD32","BRAND","XLD","RED","29",BikeType.TOURING,new ArrayList<>(),null,true);


        Rent rent = new Rent(1,user,LocalDateTime.now(),tomorrow,true,"10",List.of(bike));
        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                List.of("1"));

        List<Bike> bikes = List.of(bike);

        when(bikeRepository.findAllById(request.getBikes()
                .stream()
                .map(Integer::parseInt)
                .toList())
        ).thenReturn(bikes);


        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(rentRepository.save(Mockito.any(Rent.class))).thenReturn(rent);


        Rent rentCreated = rentService.create(request).getRent();

        Assertions.assertEquals("10",rentCreated.getPrice());
        Assertions.assertEquals(LocalDateTime.now().plusDays(1).getDayOfWeek(),rentCreated.getEndDate().getDayOfWeek());


    }

    @Test
    void testCreateRentWithoutBikes()  {

        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                List.of("1"));

        List<Bike> bikes = Collections.emptyList();

        when(bikeRepository.findAllById(request.getBikes()
                .stream()
                .map(Integer::parseInt)
                .toList())
        ).thenReturn(bikes);


        NoBikesFoundException result = Assertions.assertThrows(NoBikesFoundException.class, () -> rentService.create(request));

        Assertions.assertEquals("No bikes founded",result.getMessage());


    }

    @Test
    void testCreateRentAndCheckBikesStatus()  {


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        User user = new User("francisco","antilef","antilef","536254263","wfsdfsadfsfd");
        Bike bike = new Bike(1,"32BD32","BRAND","XLD","RED","29",BikeType.TOURING,new ArrayList<>(),null,true);
        Bike spyBike = Mockito.spy(bike);

        Rent rent = new Rent(1,user,LocalDateTime.now(),tomorrow,true,"10",List.of(bike));
        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                List.of("1"));

        List<Bike> bikes = List.of(spyBike);

        when(bikeRepository.findAllById(request.getBikes()
                .stream()
                .map(Integer::parseInt)
                .toList())
        ).thenReturn(bikes);


        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(rentRepository.save(Mockito.any(Rent.class))).thenReturn(rent);


        CreateRentResponse result = rentService.create(request);

        List<Bike> bikesResult = result.getBikes();


        Assertions.assertEquals(bikesResult.getFirst().getRents().getFirst().getId(),result.getRent().getId());
        Assertions.assertEquals("10",result.getRent().getPrice());
        Assertions.assertTrue(result.getRent().isActivate());

        Assertions.assertFalse(bikesResult.getFirst().isAvailable());

        verify(spyBike,Mockito.times(1)).setAvailable(false);

    }

    @Test
    void testCreateRentWithMoreOneBike() {


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        User user = new User("francisco","antilef","antilef","536254263","wfsdfsadfsfd");
        Bike bike = new Bike(1,"32BD32","BRAND","XLD","RED","29",BikeType.TOURING,new ArrayList<>(),null,true);
        Bike bike2 = new Bike(2,"31BD31","BRAND","XLD","RED","29",BikeType.TOURING,new ArrayList<>(),null,true);
        Bike bike3 = new Bike(3,"33BD33","BRAND","XLD","RED","29",BikeType.TOURING,new ArrayList<>(),null,true);

        Rent rentMock = new Rent(1,user,LocalDateTime.now(),tomorrow,true,"10",List.of(bike));
        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                List.of(
                        "1","2","3"
                ));

        List<Bike> mockBikes = List.of(bike,bike2,bike3);

        when(bikeRepository.findAllById(request.getBikes()
                .stream()
                .map(Integer::parseInt)
                .toList())
        ).thenReturn(mockBikes);


        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(rentRepository.save(Mockito.any(Rent.class))).thenReturn(rentMock);



        CreateRentResponse result = rentService.create(request);

        List<Bike> bikes = result.getBikes();
        Rent rent = result.getRent();

        Assertions.assertEquals(3,bikes.size());


        Assertions.assertEquals(rent.getId(),bikes.get(0).getRents().getFirst().getId());
        Assertions.assertEquals(rent.getId(),bikes.get(1).getRents().getFirst().getId());
        Assertions.assertEquals(bikes.get(0).getRents().getFirst().getId(),bikes.get(1).getRents().getFirst().getId());


    }
}




