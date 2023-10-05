package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

//    Reserve save(Reserve reserve);

    Reserve findByReserveId(Long reserveId);
    List<Reserve> findByUserId(User userId); // Reserve 아니면 Null 둘 중하나 선택
    List<Reserve> findByAccommodationId(Accommodation accommodationId);

    @NotNull
    List<Reserve> findAll();

    @Query(value = "SELECT reserve " +
            "FROM Reserve reserve " +
            "WHERE :date >= reserve.reserveDate and :date <= reserve.endDate "+
            "and reserve.accommodationId.accommodationType= :type "+
            "and reserve.accommodationId.region= :region"
    )
    List<Reserve> findReservedAccommodation(@Param("type") AccommodationType type, @Param("region") Region region, @Param("date") Date date);
}
