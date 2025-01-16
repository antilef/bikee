package cl.antilef.bikeer.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponse {
    String token;
    long expiredIn;
    Integer userId;
    String email;
    String firstName;
    String lastName;
}
