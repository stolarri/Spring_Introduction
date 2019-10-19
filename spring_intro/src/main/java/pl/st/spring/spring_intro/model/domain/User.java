package pl.st.spring.spring_intro.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, name = "first_name")
    private String first_name;
    @Column(nullable = false, name = "last_name")
    private String last_name;
    @Column(nullable = false)
    private String active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                first_name.equals(user.first_name) &&
                last_name.equals(user.last_name) &&
                active.equals(user.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, first_name, last_name, active);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
