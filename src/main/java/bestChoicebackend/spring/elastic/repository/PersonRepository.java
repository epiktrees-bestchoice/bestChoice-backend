package bestChoicebackend.spring.elastic.repository;


import bestChoicebackend.spring.elastic.document.AccommodationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface PersonRepository extends ElasticsearchRepository<AccommodationDocument, String> {

    Optional<AccommodationDocument> findById(String id);
    // Spring Data JPA처럼 findBy~~ 같은 Query Method를 만들어 준다,.
    // document에서 작성한 Person 클래스를 사용한다,
}
