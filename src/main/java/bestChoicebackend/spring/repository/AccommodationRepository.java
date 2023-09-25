package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    Page<Accommodation> findByAccommodationNameContaining(String accommodationName, Pageable pageable);
}
