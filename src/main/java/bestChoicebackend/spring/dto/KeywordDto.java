package bestChoicebackend.spring.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.List;


@Getter
@Setter
public class KeywordDto {
    private Long categoryId;
    private String categoryName;
    private List<MTypeType> mTypeList;
    private List<List<KeywordType>> keywordList;

    @Getter
    @Setter
    public static class KeywordType {
        private Long keywordId;
        private String keywordName;
    }

    @Getter
    @Setter
    public static class MTypeType {
        private Long mTypeId;
        private String mTypeName;
    }
}
