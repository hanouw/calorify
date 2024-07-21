package calorify.calorify.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-increment
    private Long memIdx; //고유식별번호

    private String memId; //아이디

    private String memPw; //비밀번호

    private String memNickname; //닉네임

    private LocalDateTime memBirth; //생년월일

    private String memEmail; //이메일

    private String memSex; //성별

    private double memHeight; //키

    private double memWeight; //몸무게

    private LocalDateTime memCreatedAt; //가입시기

    private String memStatMsg; //상태 메세지

    private MemType memType; //회원 정류

    private String memImg; //회원 프로필 사진

}
