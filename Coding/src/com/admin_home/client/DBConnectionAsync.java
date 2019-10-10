package com.admin_home.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBConnectionAsync {
   // public void authenticateUser(String name, String pass, AsyncCallback<User>callback);
    public void authenticateDetails(AsyncCallback<Details>callback1);
}
