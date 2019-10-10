package com.admin_home.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable {
    private String username;
    private String password;

    @SuppressWarnings("unused")
    private User() {
        //just here because GWT wants it.
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //System.out.println(username+" "+password);
    }
    public String getUsername()
    {
        return username;
    }
    public  String getPassword()
    {
        return password;
    }

}
