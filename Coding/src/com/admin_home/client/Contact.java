package com.admin_home.client;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ToggleButton;


public class Contact implements IsSerializable {
        private String f_name;
        private String l_name;

        private long Driver_ID;

        private long ConatctNo;

        private String location;

//

        public Contact()
        {

        }
        public Contact(String f_name,
                       String l_name,
                       long Driver_ID,
                       long ContactNo,
                       String location
                       )
        {
            this.Driver_ID = Driver_ID;
            this.location = location;
            this.f_name = f_name;
            this.l_name=l_name;
            this.ConatctNo=ContactNo;
            System.out.println(Driver_ID);
        }

    public long getDriver_ID()
    {
        return Driver_ID;
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
    public long getConatctNo()
    {
        return ConatctNo;
    }

}
