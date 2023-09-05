package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @Column
    private String keywordName;

    @Column(nullable = true)
    private Long mTypeId;

    @Column(nullable = true)
    private String mTypeName;
}
