package com.admin_home.client;

import com.admin_home.server.Postgreconnection;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.maps.client.*;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import com.admin_home.server.Postgreconnection;
//import com.admin_home.server.demo;
/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
/*private static class Contact {
       private final int Dustbin_ID;
       private final Date birthday;
       private final String name;

       public Contact(String name, Date birthday, String address) {
              this.name = name;
              this.birthday = birthday;
              this.address = address;
       }
}*/



public class Admin_home implements EntryPoint {
    private DBConnectionAsync rpc;
    Button search ;
    private TextBox id;
    private TextBox pass;
    private  TabPanel tp;
    private static String s;
    private static class Admin {

        private String Dustbin_ID;
        private String Driver;
        private String Status;
        private  String Notify;

        public Admin(String Dustbin_ID, String Driver, String Status ,String Notify) {
            this.Dustbin_ID =Dustbin_ID;
            this.Driver= Driver;
            this.Status= Status;
            this.Notify = Notify;
        }

        public String getDustbin_ID() {
            return Dustbin_ID;
        }

        public String getDriver() {
            return Driver;
        }

        public String getStatus() {
            return Status;
        }
        public String getNotify() {
            return Notify;
        }
    }


    private static final List<Admin> ADMIN = Arrays.asList(new Admin("10101", s, "70", "Send Alert"));
    public Admin_home() {
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);
        AsyncCallback<Details> callback1 = new AuthenticationHandler<Details>();
        rpc.authenticateDetails(callback1);
        VerticalPanel verticalPanel = new VerticalPanel();
        ListBox location = new ListBox();
        location.addItem("Girls hostel ground floor");
        search = new Button("Search");
        id = new TextBox();
        pass = new TextBox();
        verticalPanel.add(location);
        verticalPanel.add(search);
        verticalPanel.add(id);
        verticalPanel.add(pass);


        DecoratorPanel decoratorPanel = new DecoratorPanel();
        decoratorPanel.setWidth("1480");
        decoratorPanel.setHeight("500");
        decoratorPanel.add(verticalPanel);



        // Add the widgets to the root panel.
        //RootPanel.get().add(decoratorPanel);
        tp = new TabPanel();
        tp.add(decoratorPanel, "Home");

        tp.add(new Label("stats"), "Stats");
        tp.add(new Label("Contact"), "Contact");



        // Show the 'Home' tab initially.
        tp.selectTab(0);

        tp.setWidth("1500");
        tp.setHeight("550");
        /*Add it to the root panel.*/

    }

    public void onModuleLoad() {
        RootPanel.get().add(tp);
       //
        //    RootPanel.get().add((IsWidget) this);
        CellTable<Admin> table = new CellTable<>();

        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        TextColumn<Admin> idColumn= new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getDustbin_ID();
            }
        };
        TextColumn<Admin> driverColumn= new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getDriver();
            }
        };
        TextColumn<Admin> statusColumn = new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getStatus();
            }
        };
        TextColumn<Admin> notifyColumn = new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getNotify();
            }
        };
        table.addColumn(idColumn, "Dustbin ID");
        table.addColumn(driverColumn, "DRIVER");
        table.addColumn(statusColumn, "Status");
        table.addColumn(notifyColumn, "Notify");
        table.setRowData(0, ADMIN);
        RootPanel.get().add(table);


        search.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {


                Widget sender = (Widget) event.getSource();


                if (sender.equals(search)) {
                    /*AsyncCallback<User> callback = new AuthenticationHandler<User>();
                    rpc.authenticateUser(id.getText(),
                           pass.getText(),
                    callback);*/
                    AsyncCallback<Details> callback1 = new AuthenticationHandler<Details>();
                    rpc.authenticateDetails(callback1);


                }
            }
        });
    }
    private class AuthenticationHandler<T> implements AsyncCallback<Details>{
        public void onFailure(Throwable ex) {
            HTML h = new HTML("RPC unsuccessful");

            RootPanel.get().add(h);
        }
        public void onSuccess(Details result) {

                s = result.getDriver();
                RootPanel.get().add(new HTML(s));
                RootPanel.get().add(new HTML("RPC successful"));
        }

    }



        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element

        // Create a tab panel with three tabs, each of which displays a different
        // piece of text.


    }


