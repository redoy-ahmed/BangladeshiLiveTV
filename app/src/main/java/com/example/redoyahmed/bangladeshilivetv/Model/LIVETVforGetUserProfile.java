package com.example.redoyahmed.bangladeshilivetv.Model;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class LIVETVforGetUserProfile {

    private String phone;

    private String email;

    private String name;

    private String user_id;

    private String success;

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ClassPojo [phone = "+phone+", email = "+email+", name = "+name+", user_id = "+user_id+", success = "+success+"]";
    }
}
