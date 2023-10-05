package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.domain.Mtype;
import bestChoicebackend.spring.dto.KeywordDto;
import bestChoicebackend.spring.repository.KeywordRepository;
import bestChoicebackend.spring.repository.MtypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final MtypeRepository mtypeRepository;

    public KeywordDto findByCategoryId(Long categoryId) {

        List<Mtype> mTypes = mtypeRepository.findByCategoryId(categoryId);
        if (mTypes.isEmpty()) {
            throw new NotFoundException("No data found for categoryId: " + categoryId);
        }
        KeywordDto keywordDto = new KeywordDto();
        keywordDto.setCategoryId(categoryId);
        keywordDto.setCategoryName(mTypes.get(0).getCategoryName());

        List<KeywordDto.MtypeType> mtypeList = new ArrayList<>();
        for(Mtype m : mTypes){
            mtypeList.add(new KeywordDto.MtypeType(m.getMtypeId(), m.getMtypeName()));
        }
        keywordDto.setMtypeList(mtypeList);

        List<List<KeywordDto.KeywordType>> keywordList = new ArrayList<>();
        for(Mtype m : mTypes){
            List<KeywordDto.KeywordType> keywordTypeList = new ArrayList<>();
            List<Keyword> keywords = keywordRepository.findByMtypeId(m.getMtypeId());
            for(Keyword keyword : keywords){
                keywordTypeList.add(new KeywordDto.KeywordType(keyword.getKeywordId(), keyword.getKeywordName()));
            }
            keywordList.add(keywordTypeList);
        }

        keywordDto.setKeywordList(keywordList);

        return keywordDto;
    }

    // keyword Adding init
    public void mtypeAdd(Mtype mtype){
        mtypeRepository.save(mtype);
    }

    public void keywordAdd(Keyword keyword){
        keywordRepository.save(keyword);
    }


    public void deleteMtypeAll() {
        mtypeRepository.deleteAll();
    }

    public void deleteKeywordAll() {
        keywordRepository.deleteAll();
    }
}
