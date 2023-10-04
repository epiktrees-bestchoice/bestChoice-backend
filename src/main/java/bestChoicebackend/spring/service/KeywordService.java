package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.domain.MType;
import bestChoicebackend.spring.dto.KeywordDto;
import bestChoicebackend.spring.repository.AccommodationRepository;
import bestChoicebackend.spring.repository.KeywordRepository;
import bestChoicebackend.spring.repository.MTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final MTypeRepository mTypeRepository;

    public KeywordDto findByCategoryId(Long categoryId) {

        List<MType> mTypes = mTypeRepository.findByCategoryId(categoryId);

        KeywordDto keywordDto = new KeywordDto();
        keywordDto.setCategoryId(categoryId);
        keywordDto.setCategoryName(mTypes.get(0).getCategoryName());

        List<KeywordDto.MTypeType> mTypeList = new ArrayList<>();
        for(MType m : mTypes){
            mTypeList.add(new KeywordDto.MTypeType(m.getMTypeId(), m.getMTypeName()));
        }
        keywordDto.setMTypeList(mTypeList);

        List<List<KeywordDto.KeywordType>> keywordList = new ArrayList<>();
        for(MType m : mTypes){
            List<KeywordDto.KeywordType> keywordTypeList = new ArrayList<>();
            List<Keyword> keywords = keywordRepository.findKeywordByMTypeId(m.getMTypeId());
            for(Keyword keyword : keywords){
                keywordTypeList.add(new KeywordDto.KeywordType(keyword.getKeywordId(), keyword.getKeywordName()));
            }
            keywordList.add(keywordTypeList);
        }

        keywordDto.setKeywordList(keywordList);

        return keywordDto;
    }
}
