package dat3.security.api;

import dat3.security.dto.UserWithRolesRequest;
import dat3.security.dto.UserWithRolesResponse;
import dat3.security.service.UserWithRolesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-with-role")
public class UserWithRoleController {

  UserWithRolesService userWithRolesService;

  public UserWithRoleController(UserWithRolesService userWithRolesService) {
    this.userWithRolesService = userWithRolesService;
  }

  //Anonymous users can call this.
  @PostMapping
  public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
    return userWithRolesService.addUserWithRoles (request);
  }

  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/add-role/{username}/{role}")
  public UserWithRolesResponse addRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.addRole(username, role);
  }
  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/remove-role/{username}/{role}")
  public UserWithRolesResponse removeRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.removeRole(username, role);
  }
}
