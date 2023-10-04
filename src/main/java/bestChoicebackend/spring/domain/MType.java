package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MType {
    // Title that fits the category
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mTypeId;

    @Column(nullable = true)
    private String mTypeName;

    // like pension, hotel etc...
    @Column
    private Long categoryId;

    @Column
    private String categoryName;
}
