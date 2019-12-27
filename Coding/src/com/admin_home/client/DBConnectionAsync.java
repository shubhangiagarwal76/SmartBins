package com.admin_home.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public interface DBConnectionAsync {
    public void authenticateUser(long name, String pass,int n, AsyncCallback<User> callback3);
    public void authenticateDetails(long sid, String lname, AsyncCallback<ArrayList<Details>> callback1);
    public void authenticateContact(long sid, AsyncCallback<ArrayList<Contact>> callback);
    public void locationList(long sid, AsyncCallback<ArrayList<Location>> callback2);
    public void sendSms(String phoneno, AsyncCallback<String> callback4);
}
