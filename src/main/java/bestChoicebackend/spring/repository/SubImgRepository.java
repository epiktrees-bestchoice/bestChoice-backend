package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.SubImg;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubImgRepository extends JpaRepository<SubImg, Long> {

    // 커스텀 쿼리를 이용해서 찾기
    // 그냥 하면 accommodation에서 id를 찾는 쿼리와 SubImg에서 id와 맞는 Row 찾는 쿼리 2가지로 호출하게된다.
    @Query("Select m from SubImg m WHERE m.accommodation.accommodationId = :id")
    @NotNull // id가 잘못돼서 null 값을 출력하면 에러 발생!
    List<SubImg> findByAccommodationId(final Long id);
}
