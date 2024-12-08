package cl.antilef.bikeer.rent.controller;


import cl.antilef.bikeer.common.StatusResult;
import cl.antilef.bikeer.common.WebConstant;
import cl.antilef.bikeer.rent.dto.*;
import cl.antilef.bikeer.rent.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/rents")
public class RentController {


    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }


    @GetMapping("/user/{user_id}")
    public ResponseEntity<AllRentResponse> getAllRent(@PathVariable("user_id") String userId){


        AllRentResponse response = AllRentResponse
                .builder()
                .userId(userId)
                .rents(rentService.getAll(userId))
                .build();

        return ResponseEntity.ok(response);

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
