package calorify.calorify.controller;

import calorify.calorify.domain.Meal;
import calorify.calorify.dto.NutritionDTO;
import calorify.calorify.service.MealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/meal")
public class MealController {

    private final MealService mealService;

    @PostMapping("/save")
    public Map<String, String> saveMeal(@RequestParam("image") MultipartFile image,
                                        @RequestParam("nutrients") String nutrientsJson,
                                        @RequestParam ("date") String date,
                                        @RequestParam("memId") String memId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<NutritionDTO> nutrients = objectMapper.readValue(nutrientsJson, new TypeReference<List<NutritionDTO>>() {});
        log.info("************* MealController.java / method name : saveMeal / nutrients : {}", nutrients.size());
        String result = mealService.saveMeal(image, nutrients, memId, date);
        return Map.of("RESULT", "SUCCESS");
    }

    @GetMapping("/get/{memId}/{date}")
    public Map<String, List<List<Meal>>> getMealByDate(@PathVariable String memId, @PathVariable String date){
        List<List<Meal>> mealByDate = mealService.getMealByDate(memId, date);
        return Map.of("RESULT", mealByDate);
    }

    @DeleteMapping("/delete")
    public Map<String, String> deleteOneMeal(@RequestParam String calDate,@RequestParam Long calMealNum, @RequestParam String memId){
        mealService.deleteOneMeal(calDate, calMealNum, memId);
        return Map.of("RESULT", "SUCCESS");
    }

}
