package Group3.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import Group3.Enum.ProviderType;
import Group3.Enum.RoleType;
import Group3.dto.JWTTokensDTO;
import Group3.dto.LoginResponseDTO;
import Group3.exception.EmailAlreadyExistsException;
import Group3.exception.EmailOrPasswordInvalidException;
import Group3.exception.TokenInValidException;
import Group3.model.Accounts;
import Group3.model.RefreshToken;
import Group3.repository.AccountsRepository;
import Group3.repository.RefreshTKRepository;
import Group3.utility.JwtUtil;
import Group3.utility.PasswordEncoderUtil;


@Service
public class AuthService implements AuthInterface {
	private final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private AccountsRepository accRepo;
	private RefreshTKRepository refreshRepo;
	private JwtUtil jwtUtil;

	public AuthService(AccountsRepository accRepo, RefreshTKRepository refreshRepo, JwtUtil jwtUtil) {
		this.accRepo = accRepo;
		this.refreshRepo = refreshRepo;
		this.jwtUtil = jwtUtil;
	}

	// thực hiện đăng kí
	@Transactional
	@Override
	public Accounts registerService(String email, String password) {
		email = email.trim().toLowerCase();

		if (accRepo.findByEmail(email).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}

		Accounts newAcc = new Accounts();
		newAcc.setEmail(email);
		newAcc.setPasswordHash(PasswordEncoderUtil.hashPassword(password));
		newAcc.setRole(RoleType.USER);
		newAcc.setProvider(ProviderType.LOCAL);
		Accounts savedAccount = accRepo.save(newAcc);
		return savedAccount;
	}

	// thực hiện đăng nhập
	public LoginResponseDTO loginService(String email, String password) {
		logger.info("password: {}", password);
		Accounts accounts = accRepo.findByEmail(email)
				.orElseThrow(() -> {
					return new EmailOrPasswordInvalidException("Invalid email or password");
				});

		if (!PasswordEncoderUtil.matchPassword(password, accounts.getPasswordHash())) {
			throw new EmailOrPasswordInvalidException("Invalid email or password");
		}
		logger.info("password: {}", password);
		UUID accountId = accounts.getAccountId();
		RoleType role = accounts.getRole();

		RefreshToken refreshToken = refreshRepo.findByAccount_AccountId(accountId)
				.orElseGet(() -> {
					RefreshToken newTk = new RefreshToken();
					newTk.setAccount(accounts);
					return newTk;
				});

		saveRefreshToken(refreshToken, accountId);

		return new LoginResponseDTO(
				jwtUtil.generateAccessTK(accountId, role), refreshToken.getRefreshTk());
	}

	// Sign in with google
	@Transactional
	@Override
	public LoginResponseDTO loginWithGGService(String accessToken) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken.trim());
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
				"https://www.googleapis.com/oauth2/v3/userinfo",
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<Map<String, Object>>() {
				});
		if (response.getStatusCode() == HttpStatus.OK) {
			Map<String, Object> userInfo = response.getBody();

			String email = (String) userInfo.get("email");
			Boolean emailVerified = (Boolean) userInfo.get("email_verified");
			String name = (String) userInfo.get("name");
			String picture = (String) userInfo.get("picture");

			// xác nhận email đã được Google xác minh hay chưa
			if (emailVerified != null && emailVerified) {
				Optional<Accounts> optional = accRepo.findByEmail(email);
				Accounts accounts;
				if (optional.isPresent()) {
					accounts = optional.get();
				} else {
					createGoogleAccount(email, name, picture);
					accounts = accRepo.findByEmail(email).get();
				}
				RefreshToken refreshToken = refreshRepo.findByAccount_AccountId(accounts.getAccountId()).orElseGet(
						() -> {
							RefreshToken newTk = new RefreshToken();
							newTk.setAccount(accounts);
							return newTk;
						});
				saveRefreshToken(refreshToken, accounts.getAccountId());

				return new LoginResponseDTO(jwtUtil.generateAccessTK(accounts.getAccountId(), accounts.getRole()),
						refreshToken.getRefreshTk());

			} else {
				throw new RuntimeException("Email not verified by Google");
			}
		} else {
			throw new RuntimeException("Invalid access token");
		}
	}

	// save new refresh token
	public void saveRefreshToken(RefreshToken refreshToken, UUID accountId) {
		refreshToken.setRefreshTk(jwtUtil.generateRefreshTK(accountId));
		refreshToken.setCreatedAt(LocalDateTime.now());
		refreshToken.setExpriresAt(jwtUtil.generateExTime());
		refreshRepo.save(refreshToken);
	}

	// tạo tài khoản trên APP nếu tài khoản google mới được tạo
	private Accounts createGoogleAccount(String email, String name, String picture) {
		Accounts newAcc = new Accounts();
		newAcc.setEmail(email);
		newAcc.setName(name);
		newAcc.setAvatar(picture);
		newAcc.setRole(RoleType.USER);
		newAcc.setProvider(ProviderType.GOOGLE);
		return accRepo.save(newAcc);
	}

	@Override
	public JWTTokensDTO resetAccessToken(String refreshToken) {
		if (!jwtUtil.isTokenValid(refreshToken)) {
			throw new TokenInValidException("refreshToken:"+refreshToken); 
		}
		RefreshToken token = refreshRepo.findByRefreshTk(refreshToken).orElseThrow(
			()-> new TokenInValidException("refreshToken: "+ refreshToken+" not found")
		);
		Accounts accounts = token.getAccount();
		String newAccessToken = jwtUtil.generateAccessTK(accounts.getAccountId(), accounts.getRole());
		return new JWTTokensDTO(newAccessToken);
	}
}
