package cl.antilef.bikeer.auth.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignUpResponseDTO {
    private String message;
    private String email;
    private String status;
}
