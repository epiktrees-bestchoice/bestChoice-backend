package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Accommodation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationId;

    @Column(nullable = false)
    private String accommodationName;

    @Column(nullable = false)
    private AccommodationType type; // 호텔, 모텔 리조트, 팬션, ...

    @Column(nullable = false)
    private Long price;

    @Column
    private String region;

    @Column
    private String introduce; // 사장님 한마다


}
