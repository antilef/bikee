package cl.antilef.bikeer.bike.controller;

import cl.antilef.bikeer.bike.dto.CreateBikeRequest;
import cl.antilef.bikeer.bike.dto.GetAllBikeByRentResponse;
import cl.antilef.bikeer.bike.dto.GetBikeResponse;
import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.BikeNotFoundException;
import cl.antilef.bikeer.bike.service.BikeService;
import cl.antilef.bikeer.common.StatusResult;
import cl.antilef.bikeer.common.WebConstant;
import cl.antilef.bikeer.rent.dto.CreateRentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("/rent/{id}/bikes")
    public ResponseEntity<GetAllBikeByRentResponse> getAllBikes(@PathVariable("id") String id){

        List<Bike> bikes = bikeService.getAllBikesByRent(id);
        GetAllBikeByRentResponse response = new GetAllBikeByRentResponse(bikes, StatusResult.OK,WebConstant.SUCCESS_TEXT,bikes.size());
        return ResponseEntity.ok(response) ;

    }


    @GetMapping("/{id}")
    public ResponseEntity<GetBikeResponse> getById(@PathVariable("id") String id){
        try {
            Bike bike = bikeService.getById(id);
            GetBikeResponse response = new GetBikeResponse(bike, StatusResult.OK, WebConstant.SUCCESS_TEXT);
            return ResponseEntity.ok(response) ;

        } catch (BikeNotFoundException e) {
            GetBikeResponse response = new GetBikeResponse(null, StatusResult.NOK,e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Bike> create(@RequestBody CreateBikeRequest request){
        Bike bike = bikeService.createBike(request);
        return ResponseEntity.ok(bike);
    }


}
