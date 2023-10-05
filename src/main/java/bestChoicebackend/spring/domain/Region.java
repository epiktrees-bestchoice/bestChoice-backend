package bestChoicebackend.spring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Region {
    SEOUL("SEOUL"),
    BUSAN("BUSAN"),
    JEJU("JEJU"),
    GANGWON("GANGWON"),
    INCHEON("INCHEON"),
    GYEONGSANG("GYEONGSANG"),
    JEOLLA("JEOLLA"),
    CHUNGCHEONG("CHUNGCHEONG");

    @Getter
    private final String type;

    Region(String type) {
        this.type = type;
    }

    // 문잘를 enum type으로 변환
    @JsonCreator
    public static Region from(String type) {
        for (Region t : Region.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return null;
    }

    @JsonValue
    public String getRegion() {
        return type;
    }
}
