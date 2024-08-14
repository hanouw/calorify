package calorify.calorify.repository;

import calorify.calorify.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Query("select c from Calendar c where c.member.memId = :memId")
    Calendar findCalendarByMemId(@Param("memId") String memId);
}
