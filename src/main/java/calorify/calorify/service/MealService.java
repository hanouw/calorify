package calorify.calorify.service;

import calorify.calorify.dto.NutritionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MealService {
    String saveMeal(MultipartFile image, NutritionDTO nutrients, String memId) throws IOException;
    void getMealByDate(String memId, String date);
}
