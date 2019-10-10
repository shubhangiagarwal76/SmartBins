package com.admin_home.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Details implements IsSerializable {
    private long Dustbin_ID;
    private String Driver;
    private int Status;
    private String Notify;
   public Details()
    {

    }
    public Details(long Dustbin_ID, String Driver, String Notify)
    {
        this.Dustbin_ID = Dustbin_ID;
        this.Driver = Driver;

        this.Notify = Notify;
        System.out.println(Driver);

    }
    public long getDustbin_ID()
    {
        return Dustbin_ID;
    }
    public  String getDriver()
    {
        return Driver;
    }

    public String getNotify()
    {
        return Notify;
    }
}
