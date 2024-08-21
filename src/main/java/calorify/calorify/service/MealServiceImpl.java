package calorify.calorify.service;

import calorify.calorify.domain.Meal;
import calorify.calorify.domain.Member;
import calorify.calorify.domain.ProductFile;
import calorify.calorify.dto.NutritionDTO;
import calorify.calorify.repository.MealRepository;
import calorify.calorify.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealServiceImpl implements MealService{

    private final FileUploadService fileUploadService;
    private final MealRepository mealRepository;
    private final MemberRepository memberRepository;

    // yyyy-MM-dd'T'HH:mm 형식으로 포맷터 생성
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    // yyyy-MM-dd 형식으로 포맷터 생성
    DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String saveMeal(MultipartFile image, List<NutritionDTO> nutrients, String memId, String date) throws IOException {

        ProductFile savedFile = fileUploadService.saveFile(image);
        // 입력된 날짜 문자열을 LocalDateTime으로 변환
        LocalDateTime inputDateTime = LocalDateTime.parse(date, formatter);

        Member member = memberRepository.findById(memId).orElse(null);
        List<Meal> mealList = mealRepository.findByMemId(memId);

        // date와 날짜가 같은 리스트 추출 && 시간순으로 정렬
        List<Meal> sameDateMeals = mealList.stream()
                .filter(meal -> meal.getCalDate().toLocalDate().isEqual(inputDateTime.toLocalDate())).sorted(Comparator.comparing(Meal::getCalDate)).toList();

        // nowIndex를 시간순으로 정렬된 리스트에서 inputDateTime 바로 앞에 있는 calMealNum +1로 설정
        int nowIndex = 1;
        for (Meal meal : sameDateMeals) {
            if (meal.getCalDate().isAfter(inputDateTime)) {
                break;
            }
            nowIndex = meal.getCalMealNum() + 1;
        }

        // 새로운 Meal 객체 추가
        for (NutritionDTO nutrient : nutrients) {
            Meal meal = new Meal().dtoToEntity(nutrient);
            meal.setCalDate(inputDateTime);
            meal.setCalMealNum(nowIndex);
            meal.setMember(member);
            meal.setCalImg(savedFile.getOrgFileName());
            meal.setCalImgStored(savedFile.getStoredFileName());

            mealList.add(nowIndex - 1, meal); // nowIndex는 1-based이므로 -1을 해줌
        }

        // date 뒤 시간대 항목들 calMealNum +1 (뒤로 밀기)
        for (Meal meal : mealList) {
            if (meal.getCalDate().isAfter(inputDateTime)) {
                meal.setCalMealNum(meal.getCalMealNum() + 1);
            }
        }

        mealRepository.saveAll(mealList);
        return "SUCCESS";
    }


    @Override
    public List<List<Meal>> getMealByDate(String memId, String date) {
        log.info("************* MealServiceImpl.java / method name : getMealByDate / memId : {}", memId);
        log.info("************* MealServiceImpl.java / method name : getMealByDate / date : {}", date);
        List<Meal> mealByMemId = mealRepository.findByMemId(memId);

        // MealList를 CalMealNum으로 그룹화하고, 그 결과를 List<List<Meal>>로 변환
        ArrayList<List<Meal>> lists = new ArrayList<>(mealByMemId.stream()
                .filter(a -> Objects.equals(a.getCalDate().format(DateFormatter), date))
                .collect(Collectors.groupingBy(Meal::getCalMealNum)) // CalMealNum으로 그룹화
                .values());// Map의 값들만을 List로 변환

        log.info("************* MealServiceImpl.java / method name : getMealByDate / lists : {}", lists.size());
        log.info("************* MealServiceImpl.java / method name : getMealByDate / lists : {}", lists);

        return lists;
    }

    @Override
    @Transactional
    public void deleteOneMeal(String mealId, String memId) {
        int removedNum = mealRepository.deleteMealByMealId(Long.parseLong(mealId));
        log.info("************* MealServiceImpl.java / method name : deleteOneMeal / removedNum : {}", removedNum);
    }
}
