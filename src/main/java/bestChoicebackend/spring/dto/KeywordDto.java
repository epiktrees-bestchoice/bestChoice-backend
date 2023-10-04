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


        public KeywordType(Long keywordId, String keywordName) {
            this.keywordId = keywordId;
            this.keywordName = keywordName;
        }
    }

    @Getter
    @Setter
    public static class MTypeType {
        private Long mTypeId;
        private String mTypeName;

        public MTypeType(Long mTypeId, String mTypeName) {
            this.mTypeId = mTypeId;
            this.mTypeName = mTypeName;
        }
    }
}
