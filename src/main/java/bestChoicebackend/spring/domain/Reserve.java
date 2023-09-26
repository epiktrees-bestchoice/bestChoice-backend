package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reserve {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveId;

    @ManyToOne(cascade = CascadeType.REMOVE) // 현재 클래스 : 목표 클래스 = N : 1
    @JoinColumn(name = "userId", nullable = false, columnDefinition = "Long")
    public User userId;

    @ManyToOne(cascade = CascadeType.REMOVE) // 현재 클래스 : 목표 클래스 = N : 1
    @JoinColumn(name = "accommodationId", nullable = false, columnDefinition = "Long")
    public Accommodation accommodationId;

    @Column
    private Date reserveDate; // 예약 시작 시간

    @Column
    private Long stayDay; // 예약 기간

}
