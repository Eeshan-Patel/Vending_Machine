package SOFT2412_A2;

public class Customer {
    private String userName;
    private String passWord;
    private String role;
    private UserStrategy userStrategy;

    public Customer(String userName, String password, String role)  {
        this.userName = userName;
        this.passWord = password;
        this.role = role;
    }
}
