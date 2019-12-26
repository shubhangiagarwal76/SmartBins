package com.admin_home.client;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import java.util.ArrayList;

public interface DBConnection extends RemoteService{
    public User authenticateUser(long name, String pass, int n);
    public ArrayList<Details> authenticateDetails(long sid, String lname);
    public  ArrayList<Contact> authenticateContact(long sid);
    public  ArrayList<Location> locationList(long sid);
}
