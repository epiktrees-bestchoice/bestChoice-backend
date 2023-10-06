package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.domain.Mtype;
import bestChoicebackend.spring.dto.KeywordDto;
import bestChoicebackend.spring.service.AccommodationKeywordService;
import bestChoicebackend.spring.service.KeywordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class KeywordController {
    private final KeywordService keywordService;
    private final AccommodationKeywordService accommodationKeywordService;
    public KeywordController(KeywordService keywordService, AccommodationKeywordService accommodationKeywordService) {
        this.keywordService = keywordService;
        this.accommodationKeywordService = accommodationKeywordService;
    }

    @GetMapping("/keywords/{categoryId}")
    public KeywordDto  findByCategoryId(@PathVariable("categoryId") Long categoyId){
        return keywordService.findByCategoryId(categoyId);
    }

    @PostMapping("/mtype")
    public Mtype MtypeAdd(Mtype mtype){
        keywordService.mtypeAdd(mtype);
        return mtype;
    }

    @PostMapping("/keyword")
    public Keyword KeywordAdd(Keyword keyword){
        keywordService.keywordAdd(keyword);
        return keyword;
    }

    @DeleteMapping("/mtypeinit")
    public void MtypeInit(){
        keywordService.deleteMtypeAll();
    }

    @DeleteMapping("/keywordinit")
    public void keywordInit(){
        keywordService.deleteKeywordAll();
    }

    @PostMapping("/accommodationkeyword/init")
    public Long createInitKeyword(){
        return accommodationKeywordService.createInitKeyword();
    }
    @GetMapping("/accommodationkeyword/{accommodationId}")
    public List<KeywordDto.KeywordType> findByAccommodationId(@PathVariable("accommodationId") Long accommodationId){
        return accommodationKeywordService.findByAccommodationId(accommodationId);
    }
}
