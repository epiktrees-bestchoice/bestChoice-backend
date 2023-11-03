package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subImgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodationId", columnDefinition = "Long")
    private Accommodation accommodation;

    @Column
    private String subImgUrl;

}
