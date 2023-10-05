package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.domain.Mtype;
import bestChoicebackend.spring.dto.KeywordDto;
import bestChoicebackend.spring.service.AccommodationService;
import bestChoicebackend.spring.service.KeywordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class KeywordController {
    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/keywords/{categoryId}")
    public KeywordDto  findByCategoryId(@PathVariable("categoryId") Long categoyId){
        return keywordService.findByCategoryId(categoyId);
    }

    @PostMapping("mtype")
    public void MtypeAdd(Mtype mtype){
        keywordService.mtypeAdd(mtype);
    }

    @PostMapping("keyword")
    public void KeywordAdd(Keyword keyword){
        keywordService.keywordAdd(keyword);
    }

}
