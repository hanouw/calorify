package calorify.calorify.service;

import calorify.calorify.domain.Meal;
import calorify.calorify.dto.NutritionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MealService {
    String saveMeal(MultipartFile image, List<NutritionDTO> nutrients, String memId, String date) throws IOException;
    List<List<Meal>> getMealByDate(String memId, String date);
    void deleteOneMeal(String mealId, String memId);
}
