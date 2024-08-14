package calorify.calorify.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class MealSaveDTO {
    private MultipartFile image;
    private List<NutritionDTO> nutrients;
    private String memId;
}