package bestChoicebackend.spring.elastic.service;

import bestChoicebackend.spring.elastic.document.Person;
import bestChoicebackend.spring.elastic.repository.PersonRepository;
import bestChoicebackend.spring.exception.BaseException;
import bestChoicebackend.spring.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import static bestChoicebackend.spring.exception.BaseResponseStatus.RESPONSE_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository repository;

    public void save(final Person person){
        log.info("save id : "+person.getId()+" name : "+person.getName());
        repository.save(person);
    }

    public Person findById(final String id){
        Person person = repository.findById(id).orElse(null);
        if(person == null){
            throw new BaseException(BaseResponseStatus.RESPONSE_ERROR);
        }
        log.info("save id : "+person.getId()+" name : "+person.getName());
        return person;
    }
}
