package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.SubImg;
import bestChoicebackend.spring.dto.SubImg.SubImgResDto;
import bestChoicebackend.spring.repository.AccommodationRepository;
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
    private final AccommodationRepository accommodationRepository;

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
                        .accommodation(subImg.getAccommodation())
                        .subImgUrl(subImg.getSubImgUrl()).build()
                ) .collect(Collectors.toList());
        return subImgResDtos;
    }

    public int saveAll(){
        List<Accommodation> accommodations = accommodationRepository.findAllAccommodationId();
        List<SubImg> subImgs = new ArrayList<>();
        for(Accommodation accommodation : accommodations){
            for(int i=1; i<5;i++){
                String s = "https://d3dp03fmze904.cloudfront.net/accommodations/"+accommodation.getType()+"/"+accommodation.getType()+i+".png";
                SubImgResDto subImgResDto = SubImgResDto.builder()
                                .accommodation(accommodation)
                                        .subImgUrl(s)
                                                .build();

                subImgs.add(subImgResDto.toEntity());
            }
        }
        subImgRepository.saveAll(subImgs);
        return subImgs.size();
    }
}
