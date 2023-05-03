package dat3.security.entity;


import dat3.security.dto.UserWithRolesRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configurable
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE")
public class UserWithRoles implements UserDetails {

  @Id
  @Column(nullable = false, length = 50, unique = true)
  String username;

  @Column(nullable = false, length = 50, unique = true)
  String email;

  //60 = length of a bcrypt encoded password
  @Column(nullable = false, length = 60)
  String password;

  private boolean enabled = true;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime edited;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('USER','ADMIN')")
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "security_role")
  List<Role> roles = new ArrayList<>();

  public UserWithRoles() {
  }

  // We will use this constructor when/if users must be created via an HTTP-request
  public UserWithRoles(UserWithRolesRequest body) {
    this.username = body.getUsername();
    if (password.length() < 60) {
      throw new IllegalArgumentException("Password must be encoded with bcrypt");
    }
    this.password = body.getPassword();
    this.email = body.getEmail();
  }

  public UserWithRoles(String user, String password, String email) {
    this.username = user;
    if (password.length() < 60) {
      throw new IllegalArgumentException("Password must be encoded with bcrypt");
    }
    this.password = password;
    this.email = email;
  }

  public void setPassword(String password) {
    if (password.length() < 60) {
      throw new IllegalArgumentException("Password must be encoded with bcrypt");
    }
    this.password = password;
  }

  public void addRole(Role roleToAdd) {
    if (!roles.contains(roleToAdd)) {
      roles.add(roleToAdd);
    }
  }

  public void removeRole(Role roleToRemove) {
    if (roles.contains(roleToRemove)) {
      roles.remove(roleToRemove);
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
  }

  //You can, but are NOT expected to use the fields below
  @Override
  public boolean isAccountNonExpired() {
    return enabled;
  }

  @Override
  public boolean isAccountNonLocked() {
    return enabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return enabled;
  }
}

