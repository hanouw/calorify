package calorify.calorify.service;

import calorify.calorify.domain.Member;
import calorify.calorify.dto.MemberDTO;
import calorify.calorify.dto.MemberForm;

import java.util.Map;

public interface MemberService {
    Map<String, String> save(MemberForm memberForm);

    Member getOne(String memId);

    String delete(Long mid);

    Boolean nameIsDuplicate(String name);

    String nameAdd(String name, Long mid);
}
