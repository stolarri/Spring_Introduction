package pl.st.spring.spring_intro.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.st.spring.spring_intro.model.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByUsername(String username);
}
