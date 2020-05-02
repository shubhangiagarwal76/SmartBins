package com.admin_home.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Driver implements IsSerializable {

    private long did;
    private String pass;
    public Driver()
    {

    }
    public Driver(long did, String pass)
    {
        this.did = did;
        this.pass = pass;


    }
    public long getDid(){ return did;}
    public String getPass(){ return  pass;}

}

