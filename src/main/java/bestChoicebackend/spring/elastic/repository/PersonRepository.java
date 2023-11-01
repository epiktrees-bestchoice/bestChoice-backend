package bestChoicebackend.spring.elastic.repository;

import bestChoicebackend.spring.elastic.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {

    Optional<Person> findById(String id);
    // Spring Data JPA처럼 findBy~~ 같은 Query Method를 만들어 준다,.
    // document에서 작성한 Person 클래스를 사용한다,
}
