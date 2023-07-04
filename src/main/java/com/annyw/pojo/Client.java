package com.annyw.pojo;

import java.util.Arrays;

public class Client {
    String email;
    int id;
    String username;
    String salted_password;
    String salt;
    String privilege;
    
    
    public Client(String email, int id, String username, String salted_password,  String salt, String privilege) {
        this.email = email;
        this.id = id;
        this.username = username;
        this.salted_password = salted_password;
        this.salt = salt;
        this.privilege = privilege;
    }
    
    public Client() {
    
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getSaltedPassword() {
        return salted_password;
    }
    
    public void setSaltedPassword(String salted_password) {
        this.salted_password = salted_password;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getPrivilege() {
        return privilege;
    }
    
    public void setPrivilege(String privillege) {
        this.privilege = privillege;
    }
    
    @Override
    public String toString() {
        return "Client{" +
            "email='" + email + '\'' +
            ", id=" + id +
            ", username='" + username + '\'' +
            ", salted_password='" + salted_password + '\'' +
            ", salt=" + salt +
            ", privillege='" + privilege + '\'' +
            '}';
    }
}
