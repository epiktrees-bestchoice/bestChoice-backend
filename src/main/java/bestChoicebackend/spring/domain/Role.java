package bestChoicebackend.spring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum Role {

    // Autorization code must start with ROLE_XXX in Spring Security
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    @Getter
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

    // Role 객체를 Json으로 출력하기 위해 JsonValue라는 어트리뷰트를 사용한다.
    // role에 key 값을 넘겨준다.

    @JsonValue
    public String getKey(){
        return this.key;
    }

//    public String getTitle(){
//        return this.title;
//    }

}
