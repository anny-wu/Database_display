package com.annyw.pojo;

public class Client {
    int id;
    String username;
    String password;
    String privillege;
    
    public Client(int id, String username,  String password, String privillege) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.privillege = privillege;
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPrivillege() {
        return privillege;
    }
    
    public void setPrivillege(String privillege) {
        this.privillege = privillege;
    }
    
    @Override
    public String toString() {
        return "Client{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", privillege='" + privillege + '\'' +
            '}';
    }
}
