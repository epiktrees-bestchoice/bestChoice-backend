package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mtype {
    // Title that fits the category
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mtypeId;

    @Column(nullable = true)
    private String mtypeName; // 이색테마, 스파시설...

    // like pension, hotel etc...
    @Column
    private Long categoryId;

    @Column
    private String categoryName; //HOTEL
}
