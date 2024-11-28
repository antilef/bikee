package cl.antilef.bikeer.auth.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDTO {

    private String firstName;
    private String email;
    private String lastName;
    private String password;
    private String phone;
}
