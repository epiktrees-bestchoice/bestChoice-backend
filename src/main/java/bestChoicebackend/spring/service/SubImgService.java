package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.SubImg;
import bestChoicebackend.spring.dto.SubImg.SubImgResDto;
import bestChoicebackend.spring.repository.SubImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubImgService {

    private final SubImgRepository subImgRepository;

    public List<SubImgResDto> findByAccommodationId(Long id){
        List<SubImg> subImgs = subImgRepository.findByAccommodationId(id);
//        List<SubImgResDto> subImgResDtos = new ArrayList<>();
//        for(SubImg subImg : subImgs){
//            SubImgResDto subImgResDto = SubImgResDto.builder()
//                    .subImgId(subImg.getSubImgId())
//                    .accommodationId(subImg.getAccommodationId().getAccommodationId())
//                    .subImgUrl(subImg.getSubImgUrl()).build();
//            subImgResDtos.add(subImgResDto);
//        }
        List<SubImgResDto> subImgResDtos = subImgs.stream()
                .map(subImg -> SubImgResDto.builder()
                        .subImgId(subImg.getSubImgId())
                        .accommodationId(subImg.getAccommodationId().getAccommodationId())
                        .subImgUrl(subImg.getSubImgUrl()).build()
                ) .collect(Collectors.toList());
        return subImgResDtos;
    }


}
