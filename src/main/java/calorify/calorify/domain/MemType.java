package calorify.calorify.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemType {
    USER("STATUS_USER"), SOCIAL("STATUS_SOCIAL"), ADMIN("STATUS_ADMIN"), QUIT("STATUS_QUIT"), BANNED("STATUS_BANNED");
    private final String value;
}
