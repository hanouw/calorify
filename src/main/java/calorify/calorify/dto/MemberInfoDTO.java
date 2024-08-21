package calorify.calorify.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDTO {
    private String memNickname; //닉네임
    private String memEmail; //이메일
    private String memSex; //성별
    private double memHeight; //키
    private double memWeight; //몸무게
    private String memStatMsg; //상태 메세지
    private String memImg; //회원 프로필 사진
    private String memImgStored; //회원 프로필 사진 저장이름
}
