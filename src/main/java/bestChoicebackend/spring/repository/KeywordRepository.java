package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.domain.MType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword,  Long> {

    List<Keyword> findKeywordByMTypeId(Long mTypeId);
    List<Keyword> findKeywordByMTypeName(String mTypeName);
}
