package calorify.calorify.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Calendar {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calIdx; // 캘린더 식별번호
    private LocalDateTime calDate; // 날짜
    private String calImg; // 이미지
    private double calCal; // 칼로리
    private int calMealNum; // 식사회차
    private String calFoodName; // 음식이름
    private double calProt; // 단백질함량
    private double calCarb; // 탄수화물 함량
    private double calFat; // 지방 함량
    @OneToOne
    private Member member; // 회원식별정보 FK
}
