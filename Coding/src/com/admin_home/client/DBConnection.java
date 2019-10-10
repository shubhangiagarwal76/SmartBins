package com.admin_home.client;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
public interface DBConnection extends RemoteService{
   // public User authenticateUser(String name, String pass);
    public Details authenticateDetails();
}
