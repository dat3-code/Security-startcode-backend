package dat3.security.userWithRoles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {

  private String username;
  private String token;
  private List<String> roles;

  public LoginResponseDTO(String userName, String token, List<String> roles) {
    this.username = userName;
    this.token = token;
    this.roles = roles;
  }
}