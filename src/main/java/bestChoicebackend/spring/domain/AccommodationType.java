package bestChoicebackend.spring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum AccommodationType {
    HOTEL("HOTEL"), //1
    MOTEL("MOTEL"), //2
    PENSION("PENSION"), //3
    GUESTHOUSE("GUESTHOUSE"), //4
    CAMPING("CAMPING"); //5

    @Getter
    private final String type;

    AccommodationType(String type) {
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

    @JsonValue
    public String getType() {
        return type;
    }
}
