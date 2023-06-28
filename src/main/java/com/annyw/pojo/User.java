package com.annyw.pojo;
import java.sql.Timestamp;

public class User {
    private int id;
    
    private String username;
    
    private int age;
    
    private Timestamp update_time;
    
    public User() {
    
    }
    
    public User(int id, String username, int age, Timestamp update_time) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.update_time =  update_time;
    }
    
    public int getid() {
        return id;
    }
    
    public void setid(int id) {
        this.id = id;
    }
    
    public String getusername() {
        return username;
    }
    
    public void setusername(String username) {
        this.username = username;
    }
    
    public int getage() {
        return age;
    }
    
    public void setage(int age) {
        this.age = age;
    }
    
    public Timestamp getupdate_time() {
        return update_time;
    }
    
    public void setupdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
    
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", age=" + age +
            ", update_time=" + update_time +
            '}';
    }
}

