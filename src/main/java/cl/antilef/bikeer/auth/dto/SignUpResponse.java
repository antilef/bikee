package cl.antilef.bikeer.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignUpResponse {
    private String message;
    private String email;
    private String status;
}
