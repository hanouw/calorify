package calorify.calorify.service;

import calorify.calorify.domain.Member;
import calorify.calorify.dto.MemberDTO;
import calorify.calorify.dto.MemberForm;
import calorify.calorify.dto.MemberInfoDTO;

import java.util.Map;

public interface MemberService {
    Map<String, String> save(MemberForm memberForm);

    MemberInfoDTO getMemberInfo(String memId);

    Member getOne(String memId);

    Boolean nameIsDuplicate(String name);

}
