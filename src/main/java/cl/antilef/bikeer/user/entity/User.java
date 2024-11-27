package cl.antilef.bikeer.user.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class User {

    @Id
    public String id;

    private String firstName;
    private String lastName;
    private String password;
    private String phone;

    public User() {}

    public User(String firstName, String lastName,String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
