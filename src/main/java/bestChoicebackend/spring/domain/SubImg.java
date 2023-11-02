package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class SubImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subImgId;

    @ManyToOne
    private Accommodation accommodationId;

    @Column
    private String subImgUrl;
}
