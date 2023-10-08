package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    Page<Accommodation> findByAccommodationNameContaining(String accommodationName, Pageable pageable);

    Optional<Accommodation> findByAccommodationId(Long accommodationId);

    List<Accommodation> findAllByType(AccommodationType type);
    List<Accommodation> findAllByTypeAndRegion(AccommodationType type, String region);
}
