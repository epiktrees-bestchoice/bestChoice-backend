package bestChoicebackend.spring.elastic.controller;


import bestChoicebackend.spring.elastic.document.AccommodationDocument;
import bestChoicebackend.spring.elastic.service.AccommodationDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v2/elastic")
public class AccommodationDocumentController {
    private final AccommodationDocumentService accommodationDocumentService;


    @PostMapping("/person")
    public void save(@RequestBody final AccommodationDocument person){
        accommodationDocumentService.save(person);
    }

    @GetMapping("/person/{id}")
    public AccommodationDocument findById(@PathVariable final String id){
        return accommodationDocumentService.findById(id);
    }


//    @PostMapping("/elasticInit")
//    public int elasticInit(){
//        return accommodationDocumentService.elasticInit();
//    }

    @GetMapping("/product/search")
    public List<AccommodationDocument> findByText(@RequestParam String text){
        return accommodationDocumentService.customFullTextSearch(text);
    }

    @GetMapping("/product/accommodation")
    public List<AccommodationDocument> findBName(@RequestParam String name){
        return accommodationDocumentService.findByAccommodationDocumentName(name);
    }
}
