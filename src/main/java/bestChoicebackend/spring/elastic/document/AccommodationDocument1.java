//package bestChoicebackend.spring.elastic.document;
//
//import bestChoicebackend.spring.domain.AccommodationType;
//import bestChoicebackend.spring.elastic.helper.Indices;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.data.elasticsearch.annotations.Setting;
//
//@Getter
//@Setter
//@Document(indexName = Indices.AccommodationDocument_INDEX)
//@Setting(settingPath = "static/es-setting.json")
//public class AccommodationDocument {
//
//    @Id
//    @Field(type = FieldType.Text
//    )
//    private String accommodationId;
//
//    @Field(type = FieldType.Text)
//    private String accommodationName;
//
//    @Field(type = FieldType.Text)
//    private String type;
//
//    @Field(type = FieldType.Text)
//    private String region;
//
//    @Field(type = FieldType.Text)
//    private String introduce; // 사장님 한마다
//}
