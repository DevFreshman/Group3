package Group3.utility;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static UUID getCurrentUserId() {
        return (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
