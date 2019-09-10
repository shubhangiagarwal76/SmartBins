package com.admin_home.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface Admin_homeServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
