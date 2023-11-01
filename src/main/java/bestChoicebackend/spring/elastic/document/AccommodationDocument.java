package bestChoicebackend.spring.elastic.document;


import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@Document(indexName = "person")
@Setting(settingPath = "static/es-setting.json")
public class AccommodationDocument {
    // RDB의 table과 대응되는 Document
    // Spring Data JPA의 Repository와 비슷하게 Document 어노테이션을 붙였다.
    // indexing하는데 id와 Name 속성을 사용
    // Field는 RDB의 Columns와 대응되는 ElasticSearch의 속성
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String accommodationName;

    @Field(type = FieldType.Text)
    private String type;

    @Field(type = FieldType.Text)
    private String region;

    @Field(type = FieldType.Text)
    private String introduce; // 사장님 한마다

}
