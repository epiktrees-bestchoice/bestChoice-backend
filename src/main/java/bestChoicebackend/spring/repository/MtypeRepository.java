package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Mtype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MtypeRepository extends JpaRepository<Mtype, Long> {
    List<Mtype> findByCategoryId(Long categoryId);

    List<Mtype> findByCategoryName(String categoryName);
}
