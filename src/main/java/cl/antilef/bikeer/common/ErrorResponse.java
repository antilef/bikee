package cl.antilef.bikeer.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponse {

    private Date date;
    private String message;
    private String description;

}
