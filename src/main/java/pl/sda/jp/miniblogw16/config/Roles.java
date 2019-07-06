package pl.sda.jp.miniblogw16.config;

public enum  Roles {
    ADMIN("ADMIN"),
    USER("USER");

    private String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
