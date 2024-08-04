package calorify.calorify.security;

import calorify.calorify.domain.Member;
import calorify.calorify.dto.MemberDTO;
import calorify.calorify.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
        // 이메일로 회원 조회하고 MemberDTO 리턴, 일치하는 이메일 없으면 예외 발생 시키기
        log.info("********** CustomUserDetailsService - loadUserByUsername - username:{}", memId);
        Optional<Member> memberOptional = memberRepository.findById(memId);
        if (memberOptional.isEmpty()) {
            throw  new UsernameNotFoundException("No Match memId");
        }
        Member member = memberOptional.get();
        return new MemberDTO(member.getMemId(), member.getMemPw(), member.getMemNickname());
    }
}
