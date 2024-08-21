package calorify.calorify.controller;

import calorify.calorify.domain.Member;
import calorify.calorify.dto.MemberForm;
import calorify.calorify.dto.MemberInfoDTO;
import calorify.calorify.service.MemberService;
import calorify.calorify.util.CustomJWTException;
import calorify.calorify.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @GetMapping("/refresh")
    public Map<String, Object> refresh(@RequestHeader("Authorization") String authHeader, String refreshToken) {
        log.info("******************** MemberController /refresh - authHeader:{}", authHeader);
        log.info("******************** MemberController /refresh - refreshToken:{}", refreshToken);
        // refreshToken 없는 경우
        if (refreshToken == null) {
            throw new CustomJWTException(("NULL_REFRESH_TOKEN"));
        }
        // authorization 값이 이상한 경우
        if (authHeader == null || authHeader.length() < 7) {
            throw new CustomJWTException("INVALID_HEADER");
        }
        // accessToken 만료되지 않은 경우
        String accessToken = authHeader.substring(7);
        if (!checkExpiredToken(accessToken)) {
            return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        }
        // accessToken 만료된 경우
        // refreshToken 검증하고, claims 리턴받아 새 토큰 생성시 사용
        Map<String, Object> claims = jwtUtil.validateToken(refreshToken);
        // 새 토큰 생성해서 전달
        String newAccessToken = jwtUtil.generateToken(claims, 10);
        String newRefreshToken = checkRemainTime((Integer) claims.get("exp"))
                ? jwtUtil.generateToken(claims, 60 * 24)
                : refreshToken;
        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }

    private boolean checkRemainTime(Integer exp) {
        Date expDate = new Date((long) exp * 1000);
        long diff = expDate.getTime() - System.currentTimeMillis();
        long diffMin = diff / (1000 * 60);
        return diffMin < 60; // 남은 시간 1시간 미만이면 true, 1시간 이상이면 false
    }

    // 토큰 만료 여부 확인 메서드 (만료=true, 만료X=false)
    private boolean checkExpiredToken(String accessToken) {
        try {
            jwtUtil.validateToken(accessToken);
        } catch (CustomJWTException e) {
            if (e.getMessage().equals("Expired")) {
                return true;
            }
        }
        return false;
    }

    // ===============================================================================
    @PostMapping("") // 회원가입
    public Map<String, String> register(@RequestBody MemberForm memberForm) {
        log.info("********** MemberController register memberForm:{}", memberForm);
        Member check = memberService.getOne(memberForm.getMemId());
        if (check != null) {
            return Map.of("error", "already exists");
        }

        Map<String, String> result = memberService.save(memberForm);
        return result;
    }

    @GetMapping("/mem-info/{mid}")
    public Map<String, MemberInfoDTO> getMemberInfo(@PathVariable String mid){
        MemberInfoDTO memberInfo = memberService.getMemberInfo(mid);
        return Map.of("RESULT", memberInfo);
    }


    @PutMapping("/{mid}") // 회원 수정
    public Map<String, String> modifyMember(@PathVariable String mid, @RequestBody MemberInfoDTO memberInfoDTO){
        memberService.modifyMember(mid, memberInfoDTO);
        return Map.of("RESULT", "SUCCESS");
    }

    @GetMapping("/{name}")
    public Map<String, Boolean> nameIsDuplicate(@PathVariable("name") String name) {
        return Map.of("RESULT", memberService.nameIsDuplicate(name));
    }

}
