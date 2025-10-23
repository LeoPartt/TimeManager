package eu.epitech.t_dev_700.utils;

public enum AuthRole {

    SELF("Self"),
    MANAGER("Manager"),
    MANAGER_OF("Manager of"),
    MEMBER_OF("Member of"),
    ADMINISTRATOR("Administrator");

    private final String role;

    AuthRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
