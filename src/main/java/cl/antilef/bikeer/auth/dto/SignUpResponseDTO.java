package cl.antilef.bikeer.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignUpResponseDTO {
    private String message;
    private String email;
    private String status;
}
