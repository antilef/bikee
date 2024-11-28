package cl.antilef.bikeer.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponseDTO {
    String token;
    long expiredIn;
}
