package bestChoicebackend.spring.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column
    private String picture;

    //@Column(nullable = false)
    @Column
    private String phoneNumber;

    //@Column(nullable = false)
    @Column
    private String nickName;

//    @Column(nullable = false)
    @Column
    private String userEmail;

//    @Column(nullable = false)
    @Column
    private String social; // KaKao, Google, facebook

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String nickName,String userEmail, String picture, Role role, String social) {
        this.name = name;
        this.nickName =nickName;
        this.userEmail = userEmail;
        this.picture = picture;
        this.role = role;
        this.social = social;
    }

    public User update(String name,String nickName,  String picture) {
        this.name = name;
        this.picture = picture;
        this.nickName = nickName;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
