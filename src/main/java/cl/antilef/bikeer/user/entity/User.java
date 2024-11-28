package cl.antilef.bikeer.user.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
public class User {

    @Id
    public String id;

    private String firstName;

    @NotNull(message = "The email is required")
    @Field("yourField")
    private String email;
    private String lastName;

    @NotNull(message = "The password is required")
    @Field("yourField")
    private String password;
    private String phone;

    public User() {}

    public User(String firstName, String lastName,String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    private User(String id,String firstName, String lastName,String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    public static User withId(String id,String firstName,String lastName,String phone){
        return new User(id,firstName,lastName,phone);
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s',email='%s']",
                id, firstName, lastName,email);
    }
}
