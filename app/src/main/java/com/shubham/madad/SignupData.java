package com.shubham.madad;

/**
 * Created by SHUBHAM on 3/20/2017.
 */

public class SignupData {

    private  String name;
    private  String surname;
    private  String dob;
    private  String mob;
    private  String email;

    public  SignupData()
    {

    }

    public SignupData(String name, String surname, String dob, String mob, String email)
    {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.mob = mob;
        this.email = email;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
