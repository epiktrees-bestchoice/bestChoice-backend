package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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
    private String imgUrl;

    @Column
    private String region;

    @Column
    private String introduce; // 사장님 한마다


    public Long getAccommodationId() {
        return accommodationId;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public String getAccommodationImgUrl(){return imgUrl;}

    public AccommodationType getType() {
        return type;
    }

    public Long getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getRegion() {
        return region;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public void setType(AccommodationType type) {
        this.type = type;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
