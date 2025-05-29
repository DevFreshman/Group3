package Group3.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import Group3.dto.UserInfoResponseDTO;
import Group3.exception.EmailNotFoundException;
import Group3.model.Accounts;
import Group3.repository.AccountsRepository;
import Group3.utility.SecurityUtil;
import jakarta.transaction.Transactional;

@Service
public class ProfileService implements ProfileInterface {
    
    private AccountsRepository accRepo;

    public ProfileService(AccountsRepository accRepo) {
        this.accRepo = accRepo;
    }
	@Override
    public UserInfoResponseDTO getUserInfo() {
        UUID accUuid = SecurityUtil.getCurrentUserId();
        Accounts accounts = accRepo.findByAccountId(accUuid).orElseThrow(
                () -> new EmailNotFoundException("Email not found in Database"));
        UserInfoResponseDTO response = UserInfoResponseDTO.builder()
                .email(accounts.getEmail())
                .name(accounts.getName())
                .dateOfBirth(accounts.getDob())
                .phone(accounts.getPhoneNumber())
                .address(accounts.getAddress())
                .avatar(accounts.getAvatar())
                .build();
        return response;
    }
	@Override
    @Transactional
    public UserInfoResponseDTO saveUserInfo(String field, String value) {
        UUID accUuid = SecurityUtil.getCurrentUserId();
        Accounts accounts = accRepo.findByAccountId(accUuid).orElseThrow(
                () -> new EmailNotFoundException("Email not found in Database"));

        switch (field) {
            case "name": {
                accounts.setName(value);
                break;
            }
            case "dateOfBirth": {
                try {
                    accounts.setDob(LocalDate.parse(value));
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format. Expecting yyyy-MM-dd.");
                }
                break;
            }
            case "phone": {
                accounts.setPhoneNumber(value);
                break;
            }
            case "address": {
                accounts.setAddress(value);
                break;
            }
            default:
                throw new IllegalArgumentException("Unknow field:" + field);
        }
        accRepo.save(accounts);
        UserInfoResponseDTO response = UserInfoResponseDTO.builder()
                .email(accounts.getEmail())
                .name(accounts.getName())
                .dateOfBirth(accounts.getDob())
                .phone(accounts.getPhoneNumber())
                .address(accounts.getAddress())
                .avatar(accounts.getAvatar())
                .build();
        return response;
    }
}
