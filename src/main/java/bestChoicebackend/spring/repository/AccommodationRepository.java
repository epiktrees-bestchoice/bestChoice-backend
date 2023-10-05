package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.domain.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    Page<Accommodation> findByAccommodationNameContaining(String accommodationName, Pageable pageable);
    Page<Accommodation> findByAccommodationTypeAndRegion(AccommodationType type, Region region, Pageable pageable);
}
