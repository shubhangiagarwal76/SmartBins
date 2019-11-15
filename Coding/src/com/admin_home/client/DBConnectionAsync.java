package com.admin_home.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public interface DBConnectionAsync {
   // public void authenticateUser(String name, String pass, AsyncCallback<User>callback);
    public void authenticateDetails(AsyncCallback<ArrayList<Details>> callback1);
    public void authenticateContact(AsyncCallback<ArrayList<Contact>> callback);
    public void locationList(AsyncCallback<ArrayList<Location>> callback2);
}
