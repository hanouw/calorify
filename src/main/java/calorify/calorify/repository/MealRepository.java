package calorify.calorify.repository;

import calorify.calorify.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("select m from Meal m where m.member.memId = :memId")
    List<Meal> findByMemId(@Param("memId") String memId);

    @Query("delete Meal m where m.mealId = :mealId")
    void deleteMealByMealId(@Param("mealId") Long mealId);
}
