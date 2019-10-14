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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.admin_home.server.Postgreconnection;
//import sun.font.Decoration;
//import com.admin_home.server.demo;
/**
 * Entry point classes define <code>onModuleLoad()</code>
 */




public class Admin_home implements EntryPoint {
    private DBConnectionAsync rpc;
    Button search ;
    private TextBox id;
    private TextBox pass;
    private  TabPanel tp;
    CellTable<Admin> table;
    VerticalPanel verticalPanel;
    ListBox location;
    DecoratorPanel decoratorPanel;

    private static class Admin {
        private String Dustbin_ID;
        private String location;

        private String Status;
        private String f_name;
        private String l_name;
        private String Notify;
        public Admin(String Dustbin_ID, String location,String Status, String f_name, String l_name, String Notify)
        {
            this.Dustbin_ID = Dustbin_ID;
            this.location = location;
            this.f_name = f_name;
            this.l_name=l_name;
            this.Status= Status;
            this.Notify = Notify;
            System.out.println(Dustbin_ID);

        }
        public String getDustbin_ID()
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
        public String getStatus()
        {
            return Status;
        }

        public String getNotify()
        {
            return Notify;
        }
    }






    public Admin_home() {



        verticalPanel = new VerticalPanel();
        location = new ListBox();
        location.addItem("Girls hostel ground floor");
        search = new Button("Search");
        id = new TextBox();
        pass = new TextBox();


        decoratorPanel = new DecoratorPanel();
        decoratorPanel.setWidth("1480");
        decoratorPanel.setHeight("500");

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
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);
        AsyncCallback<ArrayList<Details>> callback1 = new AuthenticationHandler<ArrayList<Details>>();
        rpc.authenticateDetails(callback1);
         table = new CellTable<>();

        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        TextColumn<Admin> idColumn= new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getDustbin_ID();
            }
        };
        TextColumn<Admin> locationColumn= new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getLocation();
            }
        };
        TextColumn<Admin> fnameColumn = new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getF_name();
            }
        };
        TextColumn<Admin> lnameColumn = new TextColumn<Admin>() {
            @Override
            public String getValue(Admin object) {
                return object.getL_name();
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
        table.addColumn(locationColumn, "LOCATION");
        table.addColumn(statusColumn, "Status");
        table.addColumn(fnameColumn, "FIRST NAME");
        table.addColumn(lnameColumn, "LAST NAME");
        table.addColumn(notifyColumn, "Notify");
        verticalPanel.add(location);
        verticalPanel.add(search);
        verticalPanel.add(id);
        verticalPanel.add(pass);
        verticalPanel.add(table);
        decoratorPanel.add(verticalPanel);

        /*RootPanel.get().add(table);*/
        search.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {


                Widget sender = (Widget) event.getSource();


                if (sender.equals(search)) {



                }
            }
        });
    }
    private class AuthenticationHandler<T> implements AsyncCallback<ArrayList<Details>>{

        public void onFailure(Throwable ex) {
            HTML h = new HTML("RPC unsuccessful");

            RootPanel.get().add(h);
        }
        public void onSuccess(ArrayList<Details> result) {
            List<Admin> ADMIN = new ArrayList<Admin>();
            for (Details details : result) {
                String d = Long.toString(details.getDustbin_ID());
                String l = details.getLocation();
                String f = details.getF_name();
                String ln = details.getL_name();
                String s = Integer.toString(details.getStatus());
                String n = details.getNotify();

                ADMIN.add(new Admin(d, l, s, f, ln, n));
            }
            table.setRowData(ADMIN);
            RootPanel.get().add(new HTML("RPC successful"));
        }

        }
    private class AuthenticationHandlers<T> implements AsyncCallback<ArrayList<Contact>>{

        public void onFailure(Throwable ex) {
            HTML h = new HTML("RPC unsuccessful");

            RootPanel.get().add(h);
        }
        public void onSuccess(ArrayList<Contact> result) {
            List<Admin> ADMIN = new ArrayList<Admin>();
            for (Contact contact : result) {
                String dc = Long.toString(Contact.getDriver_ID());
                String lc = Contact.getLocation();
                String fc = Contact.getF_name();
                String lnc = Contact.getL_name();
                String dc1 = Long.toString(Contact.getConatctNo());
                //String s = Integer.toString(details.getStatus());
                //String n = details.getNotify();

                ADMIN.add(new Admin(dc, lc, fc, lnc,dc1));
            }
            table.setRowData(ADMIN);
            RootPanel.get().add(new HTML("RPC successful"));
        }

    }





