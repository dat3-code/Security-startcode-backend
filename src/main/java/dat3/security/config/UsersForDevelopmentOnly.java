package dat3.security.config;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UsersForDevelopmentOnly {

  private static String PASSWORD_USED_BY_ALL = "test12";

  /*****************************************************************************************
   IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
   iT'S ONE OF THE TOP SERIOUS SECURITY FLAWS YOU CAN DO

   If you see these lines in the log-outputs on Azure, forget whatever had your attention and fix this problem

   *****************************************************************************************/
  public static void setupUserWithRoleUsers(UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder) {
    System.out.println("******************************************************************************");
    System.out.println("********** IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ************");
    System.out.println();
    System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
    System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
    System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
    System.out.println();
    System.out.println("******************************************************************************");
    UserWithRoles user1 = new UserWithRoles("user1", passwordEncoder.encode(PASSWORD_USED_BY_ALL), "user1@a.dk");
    UserWithRoles user2 = new UserWithRoles("user2", passwordEncoder.encode(PASSWORD_USED_BY_ALL), "user2@a.dk");
    UserWithRoles user3 = new UserWithRoles("user3", passwordEncoder.encode(PASSWORD_USED_BY_ALL), "user3@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
  }
}
