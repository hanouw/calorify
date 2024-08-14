package calorify.calorify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {
    private String memId;
    private String password;
    private double height;
    private double weight;
    private LocalDate memBirth;
    private String memEmail;
    private String memNickname;
    private String memSex;
}