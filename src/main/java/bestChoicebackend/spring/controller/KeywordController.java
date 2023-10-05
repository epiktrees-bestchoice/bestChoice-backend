package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.dto.KeywordDto;
import bestChoicebackend.spring.service.KeywordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
