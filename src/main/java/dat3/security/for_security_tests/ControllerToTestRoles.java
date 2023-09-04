package dat3.security.for_security_tests;

import dat3.security.userWithRoles.entity.UserWithRoles;
import dat3.security.userWithRoles.UserWithRolesRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Getter
@Setter
class TestResponse {
    String UserName;
    String message;

    public TestResponse(String userName, String message) {
        UserName = userName;
        this.message = message;
    }
}

/** Controller meant solely to test the authorization of the roles.
 * This controller is only loaded when the "test" profile is active, so it does not interfere with your real controllers
 * */
@RestController
@RequestMapping("/api/security-tests")
@Profile("test")
public class ControllerToTestRoles {

    UserWithRolesRepository userWithRolesRepository;

    public ControllerToTestRoles(UserWithRolesRepository userWithRolesRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
    }
    //No need for a service, as we only need to test the roles
    UserWithRoles getUserForTest(String username) {
        return userWithRolesRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public TestResponse getAdminUser(Principal principal) {
        String authenticatedUser = principal.getName();
        return new TestResponse(getUserForTest(authenticatedUser).getUsername(),"Admin");
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public TestResponse getUserUser(Principal principal) {
        String authenticatedUser = principal.getName();
        return new TestResponse(getUserForTest(authenticatedUser).getUsername(),"User");
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/useradmin")
    public TestResponse getUserOrAdminUser(Principal principal) {
        String authenticatedUser = principal.getName();
        return new TestResponse(getUserForTest(authenticatedUser).getUsername(),"User and/or Amin");
    }

    @GetMapping("/authenticated")
    public TestResponse getAuthenticatedUser(Principal principal) {
        String authenticatedUser = principal.getName();
        return new TestResponse(getUserForTest(authenticatedUser).getUsername(),"Authenticated user");
    }
}
