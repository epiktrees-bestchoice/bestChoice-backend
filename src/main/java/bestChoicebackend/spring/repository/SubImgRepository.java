package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.SubImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubImgRepository extends JpaRepository<SubImg, Long> {
    public List<SubImg> findByAccommodationId(Long id);
}
