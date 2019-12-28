package com.admin_home.client;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import java.util.ArrayList;
import java.util.Date;

public interface DBConnection extends RemoteService{
    public User authenticateUser(long name, String pass, int n);
    public ArrayList<Details> authenticateDetails(long sid, String lname);
    public  ArrayList<Contact> authenticateContact(long sid);
    public  ArrayList<Location> locationList(long sid);
    public String sendSms(String phoneno);
    public Driver insertInfo(String f_name, String l_name, long mobile_no, long aadhar, Date DOB, String email, String gender, String address, long sid, String location);
    public String insertDustbin(long drid, double cap);
}
