package com.admin_home.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Driver implements IsSerializable {
    private String gender;
    private String email;

    private long aadhar;
    private String f_name;
    private String l_name;
    private String address;
    private long Mobile_no;
    private String DOB;
    public Driver()
    {

    }
    public Driver(String f_name, String l_name, long mobile_no, long aadhar, String DOB, String email, String gender, String address)
    {
        this.f_name= f_name;
        this.l_name= l_name;
        this.aadhar = aadhar;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.Mobile_no = mobile_no;
        this.DOB = DOB;


    }
    public String getF_name()
    {
        return f_name;
    }
    public String getL_name()
    {
        return l_name;
    }
    public String getGender()
    {
        return gender;
    }
    public String getEmail()
    {
        return email;
    }
    public String getAddress()
    {
        return address;
    }
    public String getDOB()
    {
        return DOB;
    }
    public long getAadhar()
    {
        return aadhar;
    }

    public long getMobile_no(){ return Mobile_no;}


}

