package com.example.loginapp;

public class storingdata {

    String name, uname, email, phone, passw;

    public storingdata() {

    }

    public storingdata(String name, String uname, String email, String phone, String pass) {
        this.name = name;
        this.email = email;
        this.uname = uname;
        this.passw = pass;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return passw;
    }

    public String getPhone() {
        return phone;
    }

    public String getUname() {
        return uname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPass(String pass) {
        this.passw = pass;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
