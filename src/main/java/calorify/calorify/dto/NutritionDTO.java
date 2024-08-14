package calorify.calorify.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutritionDTO {
    @JsonProperty("ANIMAL_PLANT")
    private String ANIMAL_PLANT;
    @JsonProperty("BGN_YEAR")
    private String BGN_YEAR;
    @JsonProperty("DESC_KOR")
    private String DESC_KOR;
    @JsonProperty("NUTR_CONT1")
    private String NUTR_CONT1;
    @JsonProperty("NUTR_CONT2")
    private String NUTR_CONT2;
    @JsonProperty("NUTR_CONT3")
    private String NUTR_CONT3;
    @JsonProperty("NUTR_CONT4")
    private String NUTR_CONT4;
    @JsonProperty("NUTR_CONT5")
    private String NUTR_CONT5;
    @JsonProperty("NUTR_CONT6")
    private String NUTR_CONT6;
    @JsonProperty("NUTR_CONT7")
    private String NUTR_CONT7;
    @JsonProperty("NUTR_CONT8")
    private String NUTR_CONT8;
    @JsonProperty("NUTR_CONT9")
    private String NUTR_CONT9;
    @JsonProperty("SERVING_WT")
    private String SERVING_WT;
}
