package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, columnDefinition = "Long")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "accommodationId", nullable = false, columnDefinition = "Long")
    private Accommodation accommodationId;

    @Column
    private Date reserveDate;

    @Column
    private Date endDate;

    // Getter 메서드
    public Long getReserveId() {
        return reserveId;
    }

    public User getUserId() {
        return userId;
    }

    public Accommodation getAccommodationId() {
        return accommodationId;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    // Setter 메서드
    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setAccommodationId(Accommodation accommodationId) {
        this.accommodationId = accommodationId;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
