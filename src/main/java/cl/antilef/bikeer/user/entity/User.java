package cl.antilef.bikeer.user.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    public String id;

    private String firstName;

    private String email;

    private String lastName;

    private String password;
    private String phone;


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

    public User(String firstName, String lastName, String email, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
