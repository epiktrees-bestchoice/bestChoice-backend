package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
