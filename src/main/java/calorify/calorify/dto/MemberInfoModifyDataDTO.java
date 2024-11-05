package calorify.calorify.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoModifyDataDTO {
    private String memNickname; //닉네임
    private double memHeight; //키
    private double memWeight; //몸무게
    private String memStatMsg; //상태 메세지

    @Override
    public String toString(){
        return "memNickname: " + memNickname + "height: " + memHeight + "weight: " + memWeight + "memStatMsg: " + memStatMsg;
    }
}