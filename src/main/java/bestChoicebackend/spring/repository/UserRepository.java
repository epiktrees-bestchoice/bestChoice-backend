package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserEmail(String userEmail);
    // user email find
    Optional<User> findByName(String name);
    Optional<User> findByUserId(Long userId);
}