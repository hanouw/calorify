package calorify.calorify.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MemberDTO extends User {
    private String memId;
    private String memPw;
    private String nickName;

    public MemberDTO(String memId, String memPw, String nickName) {
        super(memId, memPw, new ArrayList<>());
        this.memPw = memPw;
        this.nickName = nickName;
        this.memId = memId;
    }

    // 현재 사용자 정보를 Map 타입으로 리턴 (JWT 위한 메서드, 추후 JWT 문자열 생성시 사용)
    // MemberDTO 리턴시 User 포함하고 있어서 문제발생 가능 -> Map 타입으로 정보만 리턴
    public Map<String, Object> getClaims() {
        Map<String, Object> map = new HashMap<>();
        map.put("memId", memId);
        map.put("nickName", nickName);
        map.put("password", memPw);
        return map;
    }
}
