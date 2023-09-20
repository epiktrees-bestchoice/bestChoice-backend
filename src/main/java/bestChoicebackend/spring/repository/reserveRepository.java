package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Reserve;

import java.util.List;
import java.util.Optional;

public interface reserveRepository {

    Reserve save(Reserve reserve);

    Optional<Reserve> findByUserId(Long userId); // Reserve 아니면 Null 둘 중하나 선택
    Optional<Reserve> findByAccommodationId(Long accommodationId);

    List<Reserve> findAll();
}
