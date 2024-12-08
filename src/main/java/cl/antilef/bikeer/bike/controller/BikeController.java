package cl.antilef.bikeer.bike.controller;

import cl.antilef.bikeer.bike.dto.GetAllBikeByRentResponseDTO;
import cl.antilef.bikeer.bike.dto.GetBikeResponseDTO;
import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.exception.BikeNotFoundException;
import cl.antilef.bikeer.bike.service.BikeService;
import cl.antilef.bikeer.common.StatusResult;
import cl.antilef.bikeer.common.WebConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("/rent/{id}")
    public ResponseEntity<GetAllBikeByRentResponseDTO> getAllBikes(@PathVariable("id") String id){

        List<Bike> bikes = bikeService.getAllBikes(id);
        GetAllBikeByRentResponseDTO response = new GetAllBikeByRentResponseDTO(bikes, StatusResult.OK,WebConstant.SUCCESS_TEXT,bikes.size());
        return ResponseEntity.ok(response) ;

    }


    @GetMapping("/{id}")
    public ResponseEntity<GetBikeResponseDTO> getById(@PathVariable("id") String id){
        try {
            Bike bike = bikeService.getById(id);
            GetBikeResponseDTO response = new GetBikeResponseDTO(bike, StatusResult.OK, WebConstant.SUCCESS_TEXT);
            return ResponseEntity.ok(response) ;

        } catch (BikeNotFoundException e) {
            GetBikeResponseDTO response = new GetBikeResponseDTO(null, StatusResult.NOK,e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }





}
