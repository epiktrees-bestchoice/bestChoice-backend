package bestChoicebackend.spring.elastic.controller;

import bestChoicebackend.spring.elastic.document.Person;
import bestChoicebackend.spring.elastic.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v2")
public class PersonController {
    private final PersonService personService;


    @PostMapping("/person")
    public void save(@RequestBody final Person person){
        personService.save(person);
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable final String id){
        return personService.findById(id);
    }
}
