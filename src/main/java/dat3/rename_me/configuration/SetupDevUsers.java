package dat3.rename_me.configuration;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import static dat3.security.config.UsersForDevelopmentOnly.setupUserWithRoleUsers;

@Controller
@Profile("dev") // Prevents this code from running on Azure, where the profile is set to "prod"
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    PasswordEncoder passwordEncoder;

    Environment env;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder, Environment env) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers(userWithRolesRepository, passwordEncoder);
    }
}
