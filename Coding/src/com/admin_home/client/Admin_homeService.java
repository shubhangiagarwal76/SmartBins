package com.admin_home.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Admin_homeService")
public interface Admin_homeService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use Admin_homeService.App.getInstance() to access static instance of Admin_homeServiceAsync
     */
    public static class App {
        private static Admin_homeServiceAsync ourInstance = GWT.create(Admin_homeService.class);

        public static synchronized Admin_homeServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
