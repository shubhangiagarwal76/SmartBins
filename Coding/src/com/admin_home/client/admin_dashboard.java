package com.admin_home.client;
import com.admin_home.server.Postgreconnection;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.maps.client.*;
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





public class admin_dashboard implements ClickHandler{
    private DBConnectionAsync rpc;
    Button search;
    ButtonCell buttonCell;
    private TextBox id;
    private TextBox pass;
    private TabPanel tp;
    CellTable<Admin> table;
    CellTable<AdminContact> tablecontact;
    VerticalPanel verticalPanel, verticalPanel1;
    ListBox location;
    DecoratorPanel decoratorPanel, decoratorPanel1;
    //int count;
    Label Home;
    Label Contact;
    HorizontalPanel hpanel;
    Anchor maps;
    private static String phone;


    //INNER ADMIN CLASS FOR HOME LIST
    private static class Admin {
        private String Dustbin_ID;
        private String location;

        private String Status;
        private String f_name;
        private String l_name;
        private  String Mobileno;
        private String Notify;

        public Admin(String Dustbin_ID, String location, String Status, String f_name, String l_name, String Mobileno, String Notify) {
            this.Dustbin_ID = Dustbin_ID;
            this.location = location;
            this.f_name = f_name;
            this.l_name = l_name;
            this.Status = Status;
            this.Mobileno=Mobileno;
            this.Notify = Notify;
            System.out.println(Dustbin_ID);

        }

        public String getDustbin_ID() {
            return Dustbin_ID;
        }

        public String getLocation() {
            return location;
        }

        public String getF_name() {
            return f_name;
        }

        public String getL_name() {
            return l_name;
        }

        public String getStatus() {
            return Status;
        }

        public String getMobileno(){ return Mobileno;}

        public String getNotify() {
            return Notify;
        }
    }

    //INNER ADMINCONTACT CLASS FOR CONTACT LIST
    private static class AdminContact {
        private String Driver_ID;
        private String location;

        private String f_name;
        private String l_name;
        private String ContactNo;
        public AdminContact(String Driver_ID, String location, String f_name, String l_name, String ContactNo)
        {
            this.Driver_ID = Driver_ID;
            this.location = location;
            this.f_name = f_name;
            this.l_name=l_name;
            this.ContactNo=ContactNo;

            System.out.println(Driver_ID);

        }
        public String getDriver_ID()
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
        public String getContactNo()
        {
            return ContactNo;
        }

    }


    /*private static class LocationList{
        private String location;
        public LocationList(String location)
        {
            this.location = location;
        }
        public String getLocation()
        {
            return location;
        }
    }*/


    public admin_dashboard() {

        verticalPanel = new VerticalPanel();
        verticalPanel1 = new VerticalPanel();
        search = new Button("Search");
        maps=new Anchor("CLICK HERE TO SEE MAPS");
        id = new TextBox();
        pass = new TextBox();
        Home=new Label("Home");
        decoratorPanel = new DecoratorPanel();
        decoratorPanel1 = new DecoratorPanel();
        tp = new TabPanel();
        Contact=new Label("Contact");
        table = new CellTable<>();
        buttonCell = new ButtonCell();
        tablecontact = new CellTable<>();
        location = new ListBox();
        hpanel= new HorizontalPanel();

    }

    public static String getPhone(){ return phone;}

    public void addingpaneldashboard(){
        search.addStyleName("gwt-searchbutton");
        search.addClickHandler(this);
        Home.addStyleName("labelhome_Stats_contact");
        Contact.addStyleName("labelhome_Stats_contact");
        decoratorPanel.setWidth("1200");
        decoratorPanel.setHeight("200");
        decoratorPanel1.setWidth("1200");
        decoratorPanel1.setHeight("200");
        hpanel.add(search);
        hpanel.add(location);
        verticalPanel.add(hpanel);
        decoratorPanel.add(verticalPanel);
        decoratorPanel1.add(verticalPanel1);
        maps.setHref("LOGIN.html");
        tp.add(decoratorPanel,Home);
        tp.add(decoratorPanel1, Contact);
        verticalPanel.add(maps);
        tp.selectTab(0);
        tp.setWidth("1200");
        tp.setHeight("100");

    }
    public void onModuleLoad()
    {
        addingpaneldashboard();
        connectionEstd();
        RootPanel.get().add(tp);
    }
    public void connectionEstd() {
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);

        AsyncCallback<ArrayList<Location>> callback2 = new locationList<ArrayList<Location>>();
        rpc.locationList(Admin_home.getUname(), callback2);
        verticalPanel.add(location);
        fillHomeList();
        fillContactList();
    }

    private static interface GetValue<C> {
        C getValue(Admin contact);
    }

        public void fillHomeList() {

            table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            TextColumn<Admin> idColumn = new TextColumn<Admin>() {
                @Override
                public String getValue(Admin object) {
                    return object.getDustbin_ID();
                }
            };
            TextColumn<Admin> locationColumn = new TextColumn<Admin>() {
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
            TextColumn<Admin> Mobileno = new TextColumn<Admin>() {
                @Override
                public String getValue(Admin object) {
                    return object.getMobileno();
                }
            };
            Column<Admin, String> notifyColumn = new Column<Admin, String>(buttonCell) {
                @Override
                public String getValue(Admin object) {

                    return "Notify";
                }};
            FieldUpdater f = new FieldUpdater<Admin, String>() {
                @Override
                public void update(int index, Admin object, String value) {
                    phone=object.getMobileno();
                    Window.alert("MESSAGE SUCCESSFULLY SEND TO: "+ object.getMobileno());
            }};
            notifyColumn.setFieldUpdater(f);



            table.addColumn(idColumn, "DUSTBIN_ID");
            table.addColumn(locationColumn, "LOCATION");
            table.addColumn(statusColumn, "STATUS");
            table.addColumn(fnameColumn, "FIRST NAME");
            table.addColumn(lnameColumn, "LAST NAME");
            table.addColumn(Mobileno,"MOBILE NUMBER");
            table.addColumn(notifyColumn,"NOTIFY");


            verticalPanel.add(table);

        }



        public void fillContactList() {
            AsyncCallback<ArrayList<Contact>> callback = new AuthenticationHandlers<ArrayList<Contact>>();
            rpc.authenticateContact(Admin_home.getUname(), callback);
            tablecontact.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            TextColumn<AdminContact> idColumncontact = new TextColumn<AdminContact>() {
                @Override
                public String getValue(AdminContact objectcontact) {
                    return objectcontact.getDriver_ID();
                }
            };
            TextColumn<AdminContact> locationconatct = new TextColumn<AdminContact>() {
                @Override
                public String getValue(AdminContact object) {
                    return object.getLocation();
                }
            };
            TextColumn<AdminContact> fnamecolumncontact = new TextColumn<AdminContact>() {
                @Override
                public String getValue(AdminContact object) {
                    return object.getF_name();
                }
            };
            TextColumn<AdminContact> lnameColumncontact = new TextColumn<AdminContact>() {
                @Override
                public String getValue(AdminContact object) {
                    return object.getL_name();
                }
            };
            TextColumn<AdminContact> contactcolumncontact = new TextColumn<AdminContact>() {
                @Override
                public String getValue(AdminContact object) {
                    return object.getContactNo();
                }
            };


            tablecontact.addColumn(idColumncontact, "DRIVER_ID");
            tablecontact.addColumn(locationconatct, "LOCATION");
            tablecontact.addColumn(fnamecolumncontact, "FIRST NAME");
            tablecontact.addColumn(lnameColumncontact, "LAST NAME");
            tablecontact.addColumn(contactcolumncontact, "CONTACT");


            verticalPanel1.add(tablecontact);

        }




//        verticalPanel.add(location);
    //verticalPanel.add(id);
    //verticalPanel.add(pass);




        /*RootPanel.get().add(table);*/

    public void onClick(ClickEvent event) {

        Widget sender = (Widget) event.getSource();
        if (sender.equals(search)) {
            AsyncCallback<ArrayList<Details>> callback1 = new AuthenticationHandler<ArrayList<Details>>();
            rpc.authenticateDetails(Admin_home.getUname(), location.getSelectedItemText(), callback1);
        }
            }


    private class AuthenticationHandler<T> implements AsyncCallback<ArrayList<Details>> {

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
                String mn = Long.toString(details.getMobile_no());
                String n = details.getNotify();

                ADMIN.add(new Admin(d, l, s, f, ln, mn, n));
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
            List<AdminContact> ADMINCONTACT = new ArrayList<AdminContact>();
            for (Contact contact : result) {
                String dc = Long.toString(contact.getDriver_ID());
                String lc = contact.getLocation();
                String fc = contact.getF_name();
                String lnc = contact.getL_name();
                String dc1 = Long.toString(contact.getConatctNo());
                //String s = Integer.toString(details.getStatus());
                //String n = details.getNotify();

                ADMINCONTACT.add(new AdminContact(dc, lc, fc, lnc,dc1));
            }
            tablecontact.setRowData(ADMINCONTACT);
            RootPanel.get().add(new HTML("RPC successful"));
        }

    }
    private class locationList<T> implements AsyncCallback<ArrayList<Location>>{


        @Override
        public void onFailure(Throwable caught) {

        }

        @Override
        public void onSuccess(ArrayList<Location> result) {

            for (Location locationList: result)
            {
                String l = locationList.getLocation();

                location.addItem(l);
            }

        }
    }
}