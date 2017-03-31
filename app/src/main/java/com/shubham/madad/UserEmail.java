package com.shubham.madad;

/**
 * Created by SHUBHAM on 3/28/2017.
 */

public class UserEmail
{
    public  UserEmail()
    {

    }


    public UserEmail(String email) {
        Email = email;
    }

    String Email;


    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
