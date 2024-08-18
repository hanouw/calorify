package calorify.calorify.service;

import calorify.calorify.domain.Calendar;
import calorify.calorify.domain.Meal;
import calorify.calorify.domain.ProductFile;
import calorify.calorify.dto.NutritionDTO;
import calorify.calorify.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealServiceImpl implements MealService{

    private final FileUploadService fileUploadService;
    private final CalendarRepository calendarRepository;

    @Override
    public String saveMeal(List<MultipartFile> image, List<NutritionDTO> nutrients, String memId) throws IOException {
        Calendar calendar = calendarRepository.findCalendarByMemId(memId);
        int nowIndex = calendar.getMealList().size()+1;
        for(int i = 0; i < image.size(); i++){
            Meal meal = new Meal().dtoToEntity(nutrients.get(i));
            meal.setCalDate(LocalDateTime.now());
            meal.setCalMealNum(nowIndex);

            ProductFile savedFile = fileUploadService.saveFile(image.get(i));

            meal.setCalImg(savedFile.getOrgFileName());
            meal.setCalImgStored(savedFile.getStoredFileName());

            calendar.getMealList().add(0, meal);
        }
        calendarRepository.save(calendar);
        return "SUCCESS";
    }

    @Override
    public List<List<Meal>> getMealByDate(String memId, String date) {
        log.info("************* MealServiceImpl.java / method name : getMealByDate / memId : {}", memId);
        log.info("************* MealServiceImpl.java / method name : getMealByDate / date : {}", date);
        Calendar calendarByMemId = calendarRepository.findCalendarByMemId(memId);
        // yyyy-MM-dd 형식으로 포맷터 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // MealList를 CalMealNum으로 그룹화하고, 그 결과를 List<List<Meal>>로 변환
        return new ArrayList<>(calendarByMemId.getMealList().stream()
                .filter(a -> Objects.equals(a.getCalDate().format(formatter), date))
                .collect(Collectors.groupingBy(Meal::getCalMealNum)) // CalMealNum으로 그룹화
                .values()); // Map의 값들만을 List로 변환
    }
}
