package com.admin_home.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.admin_home.client.Admin_homeService;

public class Admin_homeServiceImpl extends RemoteServiceServlet implements Admin_homeService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}