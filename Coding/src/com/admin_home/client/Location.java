package com.admin_home.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Location implements IsSerializable {
    private String Location;
    public Location()
    {

    }
    public Location(String Location)
    {
        this.Location = Location;
        System.out.println(Location);
    }

    public String getLocation() {
        return Location;
    }
}
