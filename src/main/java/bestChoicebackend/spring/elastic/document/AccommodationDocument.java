package bestChoicebackend.spring.elastic.document;

import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.elastic.helper.Indices;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@Document(indexName = Indices.ACCOMODATION_INDEX)
@Setting(settingPath = "static/es-setting.json")
public class AccommodationDocument {

    // 숙소의 이름뿐만 아니라 소개, 지역, 타입까지 검색할 수 있도록

    @Id
    @Field(type = FieldType.Keyword)
    private String accommodationId;

    @Field(type = FieldType.Text)
    private String accommodationName;

    @Field(type = FieldType.Text)
    private String type; // 호텔, 모텔 리조트, 팬션, ...

    @Field(type = FieldType.Text)
    private String region;

    @Field(type = FieldType.Text)
    private String introduce; // 사장님 한마디
}
