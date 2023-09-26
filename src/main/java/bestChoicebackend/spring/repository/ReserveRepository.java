package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.Reserve;
import bestChoicebackend.spring.domain.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

//    Reserve save(Reserve reserve);

    Reserve findByReserveId(Long reserveId);
    List<Reserve> findByUserId(User userId); // Reserve 아니면 Null 둘 중하나 선택
    List<Reserve> findByAccommodationId(Accommodation accommodationId);

    @NotNull
    List<Reserve> findAll();
}
