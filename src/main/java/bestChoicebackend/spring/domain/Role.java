package bestChoicebackend.spring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum Role {

    // Autorization code must start with ROLE_XXX in Spring Security
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
    Role(String key, String title){
        this.key = key;
        this.title = title;
    }

    @JsonCreator
    public static Role from(String key){
        for(Role r : Role.values()){
            if(r.getKey().equals(key)) {
                return r;
            }
        }
        return null;
    }

    @JsonValue
    public String getKey(){
        return this.key;
    }

    @JsonValue
    public String getTitle(){
        return this.title;
    }

}
