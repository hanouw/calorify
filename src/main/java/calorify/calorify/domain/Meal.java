package calorify.calorify.domain;

import calorify.calorify.dto.NutritionDTO;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    private LocalDateTime calDate; // 날짜
    private String calImg; // 이미지
    private String calImgStored; // 저장된 이미지명
    private int calMealNum; // 식사회차
    private String calFoodName; // 음식이름 DESC_KOR;
    private String calAnimalPlant; // 가공 업체
    private String calBgnYear; // 구축 년도
    private String calCal; // NUTR_CONT1 열량
    private String calCarb; // NUTR_CONT2 탄수화물
    private String calProt; // NUTR_CONT3 단백질
    private String calFat; // NUTR_CONT4 지방
    private String calSug; // NUTR_CONT5 당류
    private String calNat; // NUTR_CONT6 나트륨
    private String calCol; // NUTR_CONT7 콜레스테롤
    private String calSfa; // NUTR_CONT8 포화지방산
    private String calTra; // NUTR_CONT9 트랜스 지방산
    private String calServingWt; // SERVING_WT 1회 제공량


    public Meal dtoToEntity(NutritionDTO nutritionDTO){
        this.calFoodName = nutritionDTO.getDESC_KOR();
        this.calAnimalPlant = nutritionDTO.getANIMAL_PLANT();
        this.calBgnYear = nutritionDTO.getBGN_YEAR();
        this.calCal = nutritionDTO.getNUTR_CONT1();
        this.calCarb = nutritionDTO.getNUTR_CONT2();
        this.calProt = nutritionDTO.getNUTR_CONT3();
        this.calFat = nutritionDTO.getNUTR_CONT4();
        this.calSug = nutritionDTO.getNUTR_CONT5();
        this.calNat = nutritionDTO.getNUTR_CONT6();
        this.calCol = nutritionDTO.getNUTR_CONT7();
        this.calSfa = nutritionDTO.getNUTR_CONT8();
        this.calTra = nutritionDTO.getNUTR_CONT9();
        this.calServingWt = nutritionDTO.getSERVING_WT();
        return this;
    }
}


