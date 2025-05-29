package Group3.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group3.dto.UserInfoResponseDTO;
import Group3.dto.UserInfoReqestDTO;
import Group3.service.ProfileInterface;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private ProfileInterface profileService;

    public ProfileController(ProfileInterface profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/get/info")
    public ResponseEntity<UserInfoResponseDTO> getUserInfoAPI() {
        UserInfoResponseDTO response = profileService.getUserInfo();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save/info")
    public ResponseEntity<UserInfoResponseDTO> saveUserInfoAPI(@RequestBody UserInfoReqestDTO request) {
        UserInfoResponseDTO response = profileService.saveUserInfo(request.getField(), request.getValue());
        return ResponseEntity.ok(response);
    }
    
    

}
