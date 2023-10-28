package bestChoicebackend.spring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum AccommodationType {
    HOTEL(1, "HOTEL"),
    MOTEL(2, "MOTEL"),
    PENSION(3, "PENSION"),
    GUESTHOUSE(4, "GUESTHOUSE"),
    CAMPING(5, "CAMPING");

    private final int value;
    private final String type;

    AccommodationType(int value, String type) {
        this.value = value;
        this.type = type;
    }

    // 문잘를 enum type으로 변환
    @JsonCreator
    public static AccommodationType from(String type) {
        for (AccommodationType t : AccommodationType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return null;
    }

    @JsonCreator
    public static AccommodationType from(int value) {
        for (AccommodationType t : AccommodationType.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null; // 해당하는 Enum 값이 없을 때 처리
    }

    @JsonValue
    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
}
