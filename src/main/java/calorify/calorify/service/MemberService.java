package calorify.calorify.service;

import calorify.calorify.domain.Member;
import calorify.calorify.dto.MemberDTO;
import calorify.calorify.dto.MemberForm;
import calorify.calorify.dto.MemberInfoDTO;
import calorify.calorify.dto.MemberInfoModifyDataDTO;

import java.util.Map;

public interface MemberService {
    Map<String, String> save(MemberForm memberForm);

    Member getOne(String memId);

    MemberInfoDTO getMemberInfo(String memId);

    void modifyMember(String memId, MemberInfoModifyDataDTO memberInfoDTO);

    Boolean nameIsDuplicate(String name);

}
