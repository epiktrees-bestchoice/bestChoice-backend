package bestChoicebackend.spring.domain;

import bestChoicebackend.spring.exception.BaseExceptionOld;
import bestChoicebackend.spring.exception.BaseResponseStatusOld;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccommodationType {
    HOTEL(0, "HOTEL"),
    MOTEL(1, "MOTEL"),
    PENSION(2, "PENSION"),
    GUESTHOUSE(3, "GUESTHOUSE"),
    CAMPING(4,"CAMPING");

    private final int value;
    private final String type;

    AccommodationType(int value, String type) {
        this.value = value;
        this.type = type;
    }

    // 문잘를 enum type으로 변환
    @JsonCreator
    public static AccommodationType from(String type) throws BaseExceptionOld {
        for (AccommodationType t : AccommodationType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        throw new BaseExceptionOld(BaseResponseStatusOld.TYPE_NOT_FOUND);
    }

    @JsonCreator
    public static AccommodationType from(int value) {
        for (AccommodationType t : AccommodationType.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        throw new BaseExceptionOld(BaseResponseStatusOld.TYPE_NOT_FOUND); // 해당하는 Enum 값이 없을 때 처리
    }

    @JsonValue
    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
}
