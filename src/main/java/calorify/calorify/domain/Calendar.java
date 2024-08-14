package calorify.calorify.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calIdx; // 캘린더 식별번호
    @OneToOne
    private Member member; // 회원식별정보 FK
    @ElementCollection
    @Builder.Default
    private List<Meal> mealList = new ArrayList<>();
}
