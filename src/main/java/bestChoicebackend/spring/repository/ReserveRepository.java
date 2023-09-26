package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    Reserve save(Reserve reserve);

    List<Reserve> findByUserId(Long userId); // Reserve 아니면 Null 둘 중하나 선택
    List<Reserve> findByAccommodationId(Long accommodationId);

    List<Reserve> findAll();
}
