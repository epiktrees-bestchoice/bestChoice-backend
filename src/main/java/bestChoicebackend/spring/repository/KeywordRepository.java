package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    List<Keyword> findByMtypeId(Long mtypeId);
    List<Keyword> findByMtypeName(String mtypeName);
}
