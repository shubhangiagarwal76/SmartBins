package com.admin_home.client;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import java.util.ArrayList;

public interface DBConnection extends RemoteService{
   // public User authenticateUser(String name, String pass);
    public ArrayList<Details> authenticateDetails();
    public  ArrayList<Contact> authenticateContact();
    public  ArrayList<Location> locationList();
}
