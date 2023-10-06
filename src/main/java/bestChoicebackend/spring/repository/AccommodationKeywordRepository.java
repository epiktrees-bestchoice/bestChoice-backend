package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationKeyword;
import bestChoicebackend.spring.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccommodationKeywordRepository extends JpaRepository<AccommodationKeyword, Long> {
    List<AccommodationKeyword> findByAccommodationId(Accommodation accommodationId);
    List<AccommodationKeyword> findByKeywordId(Keyword keywordId);
}
