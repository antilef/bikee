package cl.antilef.bikeer.rent.controller;

import cl.antilef.bikeer.bike.dto.GetAllBikeByRentResponse;
import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.service.BikeService;
import cl.antilef.bikeer.common.StatusResult;
import cl.antilef.bikeer.common.WebConstant;
import cl.antilef.bikeer.rent.dto.*;
import cl.antilef.bikeer.rent.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/rents")
public class RentController {


    private final RentService rentService;
    private final BikeService bikeService;

    public RentController(RentService rentService, BikeService bikeService) {
        this.rentService = rentService;
        this.bikeService = bikeService;
    }


    @GetMapping("/user/{user_id}/all")
    public ResponseEntity<AllRentResponse> getAllRent(@PathVariable("user_id") String userId){


        AllRentResponse response = AllRentResponse
                .builder()
                .userId(userId)
                .rents(rentService.getAll(userId))
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}/bikes")
    public ResponseEntity<GetAllBikeByRentResponse> getAllBikes(@PathVariable("id") String id){

        List<Bike> bikes = bikeService.getAllBikesByRent(id);
        GetAllBikeByRentResponse response = new GetAllBikeByRentResponse(bikes, StatusResult.OK,WebConstant.SUCCESS_TEXT,bikes.size());
        return ResponseEntity.ok(response) ;

    }

    @PostMapping("/create")
    public ResponseEntity<CreateRentResponse> create(@RequestBody CreateRentRequest request) {


        try {

            return ResponseEntity.ok(rentService.create(request));

        } catch (Exception e) {
            CreateRentResponse response = new CreateRentResponse(null,null);
            return ResponseEntity.badRequest().body(response);
        }



    }

    @PostMapping("/close")
    public ResponseEntity<CloseRentResponse> close(@RequestBody CloseRentRequest request){
        try{
            return ResponseEntity.ok(rentService.closeRent(request));
        } catch (Exception e){
            CloseRentResponse r = new CloseRentResponse(WebConstant.ERROR_TEXT, StatusResult.NOK,e.getMessage());
            return ResponseEntity.badRequest().body(r);
        }

    }



}
