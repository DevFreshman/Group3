package Group3.service;

import org.springframework.stereotype.Component;

import Group3.dto.JWTTokensDTO;
import Group3.dto.LoginResponseDTO;
import Group3.model.Accounts;

@Component
public interface AuthInterface {

    Accounts registerService(String email, String password);

    LoginResponseDTO loginService(String email, String password);

    LoginResponseDTO loginWithGGService(String accessToken);

    JWTTokensDTO resetAccessToken(String refreshToken);
}
