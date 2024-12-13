package cl.antilef.bikeer.rent;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.entity.BikeType;
import cl.antilef.bikeer.bike.exception.NoBikesFoundException;
import cl.antilef.bikeer.mocks.InMemoryBikeRepo;
import cl.antilef.bikeer.mocks.InMemoryRentRepo;
import cl.antilef.bikeer.rent.dto.CreateRentRequest;
import cl.antilef.bikeer.rent.dto.CreateRentResponse;
import cl.antilef.bikeer.rent.entity.Rent;
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

        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                List.of("1"));


        Rent rentCreated = rentService.create(request).getRent();

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



        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                List.of("1"));


        NoBikesFoundException result = Assertions.assertThrows(NoBikesFoundException.class, () -> {
            rentService.create(request);
        });

        Assertions.assertEquals("No bikes founded",result.getMessage());


    }

    @Test
    void createRentAndCheckBikesStatus() throws Exception {


        bikeRepository =  new InMemoryBikeRepo(createValidBikeInitialStatus());
        rentRepository = new InMemoryRentRepo(new CopyOnWriteArrayList<>());
        rentService = new RentService(rentRepository,bikeRepository);


        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                List.of("1"));



        CreateRentResponse result = rentService.create(request);

        List<Bike> bikes = result.getBikes();

        Assertions.assertEquals(bikes.getFirst().getRents().getFirst(),result.getRent().getId());
        Assertions.assertEquals("10",result.getRent().getPrice());


    }

    @Test
    void createRentWithMoreOneBike() throws Exception {


        bikeRepository =  new InMemoryBikeRepo(createValidBikeInitialStatus());
        rentRepository = new InMemoryRentRepo(new CopyOnWriteArrayList<>());
        rentService = new RentService(rentRepository,bikeRepository);


        List<String> idBikeAtStart = List.of(
                "1","2"
        );


        CreateRentRequest request = new CreateRentRequest("1",tomorrow,"10",
                idBikeAtStart);



        CreateRentResponse result = rentService.create(request);

        List<Bike> bikes = result.getBikes();
        Rent rent = result.getRent();

        Assertions.assertEquals(2,bikes.size());


        String firstBikeRentId = bikes.get(0).getRents().getFirst();
        String secondBikeRentId = bikes.get(1).getRents().getFirst();

        Assertions.assertEquals(firstBikeRentId,secondBikeRentId);
        Assertions.assertEquals(rent.getId(),firstBikeRentId);

        Assertions.assertEquals(2,rent.getBikes().size());
        Assertions.assertEquals(idBikeAtStart,rent.getBikes());

    }

    private CopyOnWriteArrayList<Bike> createValidBikeInitialStatus(){
        CopyOnWriteArrayList<Bike> bikes = new CopyOnWriteArrayList<>();

        bikes.add(new Bike("1","sdafasdf","mdk","dsfa","RED","26", BikeType.ELECTRIC,null));
        bikes.add(new Bike("2","gfgdf","mdk","dsfa","RED","27", BikeType.ELECTRIC,null));
        bikes.add(new Bike("3","srtrtysdf","mdk","dsfa","RED","24", BikeType.ELECTRIC,null));

        return bikes;
    }
}




