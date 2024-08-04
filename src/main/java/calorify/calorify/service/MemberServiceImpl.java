package calorify.calorify.service;

import calorify.calorify.domain.Member;
import calorify.calorify.dto.MemberDTO;
import calorify.calorify.dto.MemberForm;
import calorify.calorify.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Override
    public Map<String, String> save(MemberForm memberForm) {
        Member member = new Member().formToMember(memberForm);
        Member saved = memberRepository.save(member);
        return Map.of("success", saved.getMemId());
    }

    public Member getOne(String memId) {
        Optional<Member> memberOptional = memberRepository.findById(memId);
        return memberOptional.orElse(null);
    }

    @Override
    public String delete(Long mid) {
        return null;
    }

    @Override
    public Boolean nameIsDuplicate(String name) {
        return null;
    }

    @Override
    public String nameAdd(String name, Long mid) {
        return null;
    }
}
