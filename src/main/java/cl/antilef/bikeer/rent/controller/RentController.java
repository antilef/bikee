package cl.antilef.bikeer.rent.controller;


import cl.antilef.bikeer.rent.dto.AllRentResponseDTO;
import cl.antilef.bikeer.rent.dto.CreateRentRequestDTO;
import cl.antilef.bikeer.rent.dto.CreateRentResponseDTO;
import cl.antilef.bikeer.rent.entity.Rent;
import cl.antilef.bikeer.rent.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/rents")
public class RentController {


    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }


    @GetMapping("/{user_id}")
    public ResponseEntity<AllRentResponseDTO> getAllRent(@PathVariable("user_id") String userId){


        AllRentResponseDTO response = AllRentResponseDTO
                .builder()
                .userId(userId)
                .rents(rentService.getAll(userId))
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/create")
    public ResponseEntity<CreateRentResponseDTO> create(@RequestBody CreateRentRequestDTO request){



        Rent rent = rentService.create(request);


        CreateRentResponseDTO response = CreateRentResponseDTO
                .builder()
                .rent(rent)
                .build();
        return ResponseEntity.ok(response);
    }

}