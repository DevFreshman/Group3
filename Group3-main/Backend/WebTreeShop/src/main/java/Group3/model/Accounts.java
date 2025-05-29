package Group3.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import Group3.Enum.ProviderType;
import Group3.Enum.RoleType;
import Group3.Enum.StatusType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
@Builder
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BINARY(16)", name = "AccID")
	private UUID accountId;

	@Column(name = "name")
	private String name;

	@Column(name = "dateOfBirth")
	private LocalDate dob;

	@Column(name = "adress")
	private String address;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "passwordHash")
	private String passwordHash;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private RoleType role;

	@Column(name = "provider")
	@Enumerated(EnumType.STRING)
	private ProviderType provider;

	@Column(name = "status")
	private StatusType status;

	@Column(name = "avatar")
	private String avatar;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RefreshToken> refreshTokens;

}
