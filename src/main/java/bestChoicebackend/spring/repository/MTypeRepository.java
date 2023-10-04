package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.MType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MTypeRepository extends JpaRepository<MType, Long> {
    List<MType> findByCategoryId(Long categoryId);

    List<MType> findByCategoryName(String categoryName);
}
