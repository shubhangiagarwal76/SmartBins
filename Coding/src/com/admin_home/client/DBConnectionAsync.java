package com.admin_home.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.Date;

public interface DBConnectionAsync {
    public void authenticateUser(long name, String pass,int n, AsyncCallback<User> callback3);
    public void authenticateDetails(long sid, String lname, AsyncCallback<ArrayList<Details>> callback1);
    public void authenticateContact(long sid, AsyncCallback<ArrayList<Contact>> callback);
    public void locationList(long sid, AsyncCallback<ArrayList<Location>> callback2);
    public void sendSms(String phoneno, AsyncCallback<String> callback4);
    public void insertInfo(String f_name, String l_name, long mobile_no, long aadhar, Date DOB, String email, String gender, String address, long sid, String location, AsyncCallback<Driver> callback5);
    public void insertDustbin(long drid, double cap, AsyncCallback<String> callback6);
}
