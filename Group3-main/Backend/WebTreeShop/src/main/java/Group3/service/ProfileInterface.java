package Group3.service;

import org.springframework.stereotype.Component;

import Group3.dto.UserInfoResponseDTO;

@Component
public interface ProfileInterface {

    UserInfoResponseDTO getUserInfo();

    UserInfoResponseDTO saveUserInfo(String field, String value);
}
