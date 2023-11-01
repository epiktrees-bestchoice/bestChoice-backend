package bestChoicebackend.spring.controllerv2;

import bestChoicebackend.spring.elastic.service.AccommodationDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v2")
public class ElasticController {

    private final AccommodationDocumentService accommodationDocumentService;

    @PostMapping("/elasticInit")
    public int elasticInit(){
        return accommodationDocumentService.elasticInit();
    }

    @GetMapping("/product/search/{text}")
    public List<AccommodationDocument> findByText(@PathVariable String text){
        return accommodationDocumentService.customFullTextSearch(text);
    }

    @GetMapping("/product/accommodation/{name}")
    public List<AccommodationDocument> findBName(@PathVariable String name){
        return accommodationDocumentService.findByAccommodationDocumentName(name);
    }

}
