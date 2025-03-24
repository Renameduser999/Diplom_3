package userdata;

public class UserObject {
    private String email;
    private String password;
    private String name;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    //для регистрации
    public UserObject(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // для авторизации
    public UserObject(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserObject() { }

    // Геттеры и сеттеры - с помощью Lombok
}
