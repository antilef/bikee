package cl.antilef.bikeer.rent;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.entity.BikeType;
import cl.antilef.bikeer.bike.exception.NoBikesFoundException;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import cl.antilef.bikeer.mocks.InMemoryBikeRepo;
import cl.antilef.bikeer.mocks.InMemoryRentRepo;
import cl.antilef.bikeer.rent.dto.CreateRentRequestDTO;
import cl.antilef.bikeer.rent.entity.Rent;
import cl.antilef.bikeer.rent.repository.RentRepository;
import cl.antilef.bikeer.rent.service.RentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class RentServiceTest {

    private RentService rentService;
    private RentRepository rentRepository;
    private BikeRepository bikeRepository;

    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);




    @Test
    void createRentTest() throws Exception {

        //Initial state of bike repo

        bikeRepository =  new InMemoryBikeRepo(createValidBikeInitialStatus());



        rentRepository = new InMemoryRentRepo(new CopyOnWriteArrayList<>());
        rentService = new RentService(rentRepository,bikeRepository);



        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);

        CreateRentRequestDTO request = new CreateRentRequestDTO("1",tomorrow,"10",
                List.of("1"));


        Rent rentCreated = rentService.create(request);

        Assertions.assertEquals("10",rentCreated.getPrice());
        Assertions.assertEquals(LocalDateTime.now().plusDays(1).getDayOfWeek(),rentCreated.getEndDate().getDayOfWeek());


    }

    @Test
    void createRentWithoutBikesTest()  {

        //Initial state of bike repo
        CopyOnWriteArrayList<Bike> bikes = new CopyOnWriteArrayList<>();

        bikeRepository =  new InMemoryBikeRepo(bikes);



        rentRepository = new InMemoryRentRepo(new CopyOnWriteArrayList<>());
        rentService = new RentService(rentRepository,bikeRepository);



        CreateRentRequestDTO request = new CreateRentRequestDTO("1",tomorrow,"10",
                List.of("1"));


        NoBikesFoundException result = Assertions.assertThrows(NoBikesFoundException.class, () -> {
            rentService.create(request);
        });

        Assertions.assertEquals("No bikes founded",result.getMessage());


    }

    private CopyOnWriteArrayList<Bike> createValidBikeInitialStatus(){
        CopyOnWriteArrayList<Bike> bikes = new CopyOnWriteArrayList<>();

        bikes.add(new Bike("1","sdafasdf","mdk","dsfa","RED","26", BikeType.ELECTRIC,null));
        bikes.add(new Bike("2","gfgdf","mdk","dsfa","RED","27", BikeType.ELECTRIC,null));
        bikes.add(new Bike("3","srtrtysdf","mdk","dsfa","RED","24", BikeType.ELECTRIC,null));

        return bikes;
    }
}




