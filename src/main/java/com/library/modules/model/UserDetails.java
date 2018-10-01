package com.library.modules.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails
{

    @Id
    private Long    userID;
    private String  userName;
    private String  password;
    private String  role;
    private boolean enabled;

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public Long getUserID()
    {
        return userID;
    }

    public void setUserID(Long userID)
    {
        this.userID = userID;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String Role)
    {
        this.role = role;
    }

    public UserDetails(String userName, String password, String role)
    {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.enabled=true;
    }
}