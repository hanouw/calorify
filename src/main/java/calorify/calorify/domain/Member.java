package calorify.calorify.domain;

import calorify.calorify.dto.MemberForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id //Primary Key
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

    @Builder.Default
    private MemType memType = MemType.valueOf("USER"); //회원 종류

    private String memImg; //회원 프로필 사진

    public Member formToMember(MemberForm memberForm){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        this.memId = memberForm.getMemId();
        this.memPw = passwordEncoder.encode(memberForm.getPassword());
        this.memHeight = memberForm.getHeight();
        this.memWeight = memberForm.getWeight();
        this.memBirth = memberForm.getMemBirth().atStartOfDay();
        this.memEmail = memberForm.getMemEmail();
        this.memNickname = memberForm.getMemNickname();
        return this;
    }
}
