package cl.antilef.bikeer.auth.controller;

import cl.antilef.bikeer.auth.dto.*;
import cl.antilef.bikeer.auth.service.AuthService;
import cl.antilef.bikeer.auth.service.JwtService;
import cl.antilef.bikeer.common.StatusResult;
import cl.antilef.bikeer.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtService jwtService;

    private final AuthService authenticationService;


    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> register(@RequestBody SignUpRequestDTO registerUserDto) {
        try{
            User registeredUser = authenticationService.signup(registerUserDto);
            SignUpResponseDTO response = new SignUpResponseDTO("User Created",registeredUser.getEmail(),StatusResult.OK.toString());


            return ResponseEntity.ok(response);
        }catch(Exception e){
            SignUpResponseDTO response = new SignUpResponseDTO(e.getMessage(),null, StatusResult.NOK.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDTO> authenticate(@RequestBody SignInRequestDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        SignInResponseDTO loginResponse = SignInResponseDTO
                .builder()
                .token(jwtToken)
                .expiredIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
