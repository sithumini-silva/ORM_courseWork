package org.example.tdm;

public class UserTm {
    private String userId;
    private String name;
    private String role;
    private  String tel;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "UserTm{" +
                "id='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTm(String userId, String name, String role, String tel, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public UserTm() {
    }
}
