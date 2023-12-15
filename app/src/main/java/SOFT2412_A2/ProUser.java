package SOFT2412_A2;

public class ProUser {
    private String role;
    private String name;
    private String password;

    public ProUser(String role, String name, String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
