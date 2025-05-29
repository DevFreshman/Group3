package Group3.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BINARY(16)", name = "RrfreshTkID")
	private UUID refreshTkId;

	@ManyToOne
	@JoinColumn(name = "AccID", nullable = false)
	private Accounts account;
	@Column(name = "RefreshTokenValue")
	private String refreshTk;
	@Column(name = "CreatedAt")
	private LocalDateTime createdAt;
	@Column(name = "ExpiresAt")
	private LocalDateTime expriresAt;
	
}
