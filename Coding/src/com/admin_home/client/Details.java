package com.admin_home.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Details implements IsSerializable {
    private long Dustbin_ID;
    private String location;

    private int Status;
    private String f_name;
    private String l_name;
    private String Notify;
   public Details()
    {

    }
    public Details(long Dustbin_ID, String location,int Status, String f_name, String l_name, String Notify)
    {
        this.Dustbin_ID = Dustbin_ID;
        this.location = location;
        this.f_name = f_name;
        this.l_name=l_name;
        this.Status= Status;
        this.Notify = Notify;
        System.out.println(Dustbin_ID);

    }
    public long getDustbin_ID()
    {
        return Dustbin_ID;
    }
    public  String getLocation()
    {
        return location;
    }

    public String getF_name() {
        return f_name;
    }
    public String getL_name()
    {
        return l_name;
    }
    public int getStatus()
    {
        return Status;
    }

    public String getNotify()
    {
        return Notify;
    }
}
