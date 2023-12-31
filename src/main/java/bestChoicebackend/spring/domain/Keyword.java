package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Keyword {

    // Title에 맞는 keyword
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    @Column
    private String keywordName; // 거울룸, 복층룸, 파티룸

    // Title that fits the category
    @Column(nullable = true)
    private Long mtypeId;

    @Column(nullable = true)
    private String mtypeName;
}
