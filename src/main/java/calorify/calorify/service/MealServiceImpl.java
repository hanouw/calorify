package calorify.calorify.service;

import calorify.calorify.domain.Calendar;
import calorify.calorify.domain.Meal;
import calorify.calorify.domain.ProductFile;
import calorify.calorify.dto.NutritionDTO;
import calorify.calorify.repository.CalendarRepository;
import calorify.calorify.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService{

    private final FileUploadService fileUploadService;
    private final CalendarRepository calendarRepository;

    @Override
    public String saveMeal(MultipartFile image, NutritionDTO nutrients, String memId) throws IOException {
        Calendar calendar = calendarRepository.findCalendarByMemId(memId);

        Meal meal =  new Meal().dtoToEntity(nutrients);
        meal.setCalDate(LocalDateTime.now());
        meal.setCalMealNum(1);

        ProductFile savedFile = fileUploadService.saveFile(image);

        meal.setCalImg(savedFile.getOrgFileName());
        meal.setCalImgStored(savedFile.getStoredFileName());

        calendar.getMealList().add(0, meal);
        calendarRepository.save(calendar);
        return "SUCCESS";
    }

    @Override
    public void getMealByDate(String memId, String date) {
        Calendar calendarByMemId = calendarRepository.findCalendarByMemId(memId);
//        calendarByMemId.getMealList().stream().filter(a -> a.getCalDate())
    }
}
