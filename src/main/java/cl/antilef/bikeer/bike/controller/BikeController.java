package cl.antilef.bikeer.bike.controller;

import cl.antilef.bikeer.bike.dto.CreateBikeRequest;
import cl.antilef.bikeer.bike.dto.GetAvailableBikes;
import cl.antilef.bikeer.bike.dto.GetBikeResponse;
import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.BikeNotFoundException;
import cl.antilef.bikeer.bike.service.BikeService;
import cl.antilef.bikeer.common.StatusResult;
import cl.antilef.bikeer.common.WebConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
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


    @GetMapping("/availables")
    public ResponseEntity<GetAvailableBikes> getAvailableBikes(){
        List<Bike> bikes = bikeService.getAllBikesAvailables();
        GetAvailableBikes response = new GetAvailableBikes(bikes,bikes.size());
        return ResponseEntity.ok(response);
    }

}
