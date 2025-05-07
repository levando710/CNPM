/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author do lee
 */
public class User {
    private String username;
    private String password;
    private String name;
    private String email;      // <-- Thêm dòng này
    private String type;
    private String position;

    public User(String username, String password, String name, String email, String type, String position) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
        this.position = position;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }
}