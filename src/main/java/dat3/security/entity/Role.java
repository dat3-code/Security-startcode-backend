package dat3.security.entity;

/*
Add required roles for your project to this enum
 */
public enum Role {
    USER,
    ADMIN;

    public static Role fromString(String roleString) {
        return Role.valueOf(roleString.trim().toUpperCase());
    }
}
