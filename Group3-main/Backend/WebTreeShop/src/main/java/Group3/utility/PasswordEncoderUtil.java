package Group3.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String rawPassword){
        return encoder.encode(rawPassword);
    }

    public static boolean matchPassword(String rawPassword, String hashPassword){
        return encoder.matches(rawPassword, hashPassword);
    }
}
