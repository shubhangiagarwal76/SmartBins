package com.admin_home.client;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public class admin_dashboard implements ClickHandler{
    private DBConnectionAsync rpc;
    Button search;
    ButtonCell buttonCell;
    private TextBox id;
    private TextBox pass;
    private TabPanel tp;
    CellTable<Admin> table;
    CellTable<AdminContact> tablecontact;
    VerticalPanel verticalPanel, verticalPanel1,verticalPanel2;
    ListBox location;
    DecoratorPanel decoratorPanel, decoratorPanel1;
    static int count=0;
    Label Home;
    Label Contact;
    HorizontalPanel hpanel,hpanel2;
    Anchor maps;
    private static String phone;
    Timer refresh;
    Button adddriver, addDustbin;
    DisclosurePanel disclosurePanel;
    Button logout;


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

    //Constructor
    public admin_dashboard() {

        adddriver=new Button("ADD DRIVER");
        addDustbin=new Button("REGISTER DUSTBIN");
        verticalPanel = new VerticalPanel();
        verticalPanel1 = new VerticalPanel();
        search = new Button("Search");
        maps=new Anchor("CLICK HERE TO SEE MAPS","MAPS.html","_blank");
        id = new TextBox();
        pass = new TextBox();
        Home=new Label("Home");
        decoratorPanel = new DecoratorPanel();
        decoratorPanel1 = new DecoratorPanel();
        tp = new TabPanel();
        Contact=new Label("Contact");
        table = new CellTable<Admin>();
        buttonCell = new ButtonCell();
        tablecontact = new CellTable<AdminContact>();
        location = new ListBox();
        hpanel= new HorizontalPanel();
        disclosurePanel=new DisclosurePanel("OPTIONS", true);
        hpanel2=new HorizontalPanel();
        verticalPanel2=new VerticalPanel();
        logout=new Button("Logout");

    }

    //getter of phone
    public static String getPhone() {
        return phone;}

    //adding to panel
    public void addingpaneldashboard(){
        adddriver.addClickHandler(this);
        addDustbin.addClickHandler(this);
        search.addClickHandler(this);
        logout.addClickHandler(this);
        //logout.setHTML("LogOut");
       // logout.setTargetHistoryToken("page0");
        hpanel.add(location);
        hpanel.add(search);

        verticalPanel.add(hpanel);

        tp.add(verticalPanel,Home);
        tp.add(verticalPanel1, Contact);
        tp.setAnimationEnabled(true);

        //ON CHANGE OF TAB PANELS HISTORY ADDED
        tp.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                History.newItem("page"+event.getSelectedItem());
            }
        });



        //INVOCATION OF HISTORY ON TABS ONLY
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                String historyToken = event.getValue();
                try {
                    if (historyToken.substring(0, 4).equals("page")) {
                        String tabIndexToken = historyToken.substring(4, 5);
                        int tabIndex = Integer.parseInt(tabIndexToken);
                        // Select the specified tab panel
                        tp.selectTab(tabIndex);
                    } else {
                        tp.selectTab(0);
                    }

                } catch (IndexOutOfBoundsException e) {
                    tp.selectTab(0);
                }
            }
        });
        verticalPanel2.add(adddriver);
        verticalPanel2.add(addDustbin);
        verticalPanel2.add(maps);
        verticalPanel2.add(logout);
        disclosurePanel.add(verticalPanel2);
        disclosurePanel.setAnimationEnabled(true);
        tp.selectTab(0);
        hpanel2.add(disclosurePanel);
        verticalPanel2.setSpacing(10);

        hpanel2.add(tp);

        hpanel.setSpacing(10);
        disclosurePanel.setStyleName("disclose");
        addDustbin.setStyleName("gwt-searchbutton");
        adddriver.setStyleName("gwt-searchbutton");
        Home.addStyleName("labelhome_Stats_contact");
        tp.setStyleName("tabStyle");
        search.setStyleName("gwt-searchbutton");
        search.addStyleName("gwt-searchbutton span");
        Contact.addStyleName("labelhome_Stats_contact");
        location.setStyleName("locationStyle");
        maps.setStyleName("hyper");
        logout.setStyleName("gwt-searchbutton");
        table.setStyleName("admin_table");
        tablecontact.setStyleName("admin_table");
        RootPanel.get().setStyleName("root");
        hpanel2.setStyleName("hpanel");
        hpanel.setStyleName("searchpanel");
    }

    //ON module load
    public void onModuleLoad() {
        addingpaneldashboard();
        connectionEstd();
        RootPanel.get().add(hpanel2);
    }

    //RPC Connection
    public void connectionEstd() {
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);

        AsyncCallback<ArrayList<Location>> callback2 = new locationList<ArrayList<Location>>();
        rpc.locationList(Admin_home.getUname(), callback2);
        hpanel.add(location);
        fillHomeList();
        fillContactList();
    }

    //FILL HOME TAB
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
                AsyncCallback <String> callback4 = new sendsms<String>();
                rpc.sendSms(phone, callback4);
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

    //FILL CONTACT TAB
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

    //AUTOMATIC REFRESH RATE
    public void refreshstatus() {
        AsyncCallback<ArrayList<Details>> callback1 = new AuthenticationHandler<ArrayList<Details>>();
        rpc.authenticateDetails(Admin_home.getUname(), location.getSelectedItemText(), callback1);
    }

    //EVENT HANDLER
    public void onClick(ClickEvent event) {

        Widget sender = (Widget) event.getSource();

        if (sender.equals(search))
        {
            count++;
            AsyncCallback<ArrayList<Details>> callback1 = new AuthenticationHandler<ArrayList<Details>>();
            rpc.authenticateDetails(Admin_home.getUname(), location.getSelectedItemText(), callback1);
            refresh = new Timer() {

                @Override
                public void run() {
                    if (count>=2)

                    {
                        refresh.cancel();
                        count =0;
                        refreshstatus();
                    }
                    else
                        refreshstatus();
                }
            };
            refresh.scheduleRepeating(5000);

        }
        if(sender.equals(adddriver))
        {
            AddDriver add1=new AddDriver();
            int left = Window.getClientWidth()/ 2;
            int top = Window.getClientHeight()/ 2;
            add1.setPopupPosition(left, top);
            add1.center();
        }
        if(sender.equals(addDustbin)){

            AddDustbin ad = new AddDustbin();
            int left = Window.getClientWidth()/ 2;
            int top = Window.getClientHeight()/ 2;
            ad.setPopupPosition(left, top);
            ad.center();

        }

        if(sender.equals(logout))
        {

            Window.Location.reload();
        }
    }

    //RPC SUCCESSFUL FOR HOME LIST
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
                String s = Integer.toString(details.getStatus()* Random.nextInt(5));
                String mn = Long.toString(details.getMobile_no());
                String n = details.getNotify();

                ADMIN.add(new Admin(d, l, s, f, ln, mn, n));
            }
            table.setRowData(ADMIN);
        }

    }

    //RPC SUCCESSFUL FOR CONTACT LIST
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

        }

    }

    //RPC SUCCESSFUL FOR LOCATION DROPDOWN
    private class locationList<T> implements AsyncCallback<ArrayList<Location>> {


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

    //SEND MESSAGE CLASS
    private class sendsms<T> implements AsyncCallback<String>{


        @Override
        public void onFailure(Throwable caught) {

        }

        @Override
        public void onSuccess(String result) {

            result=getPhone();
            System.out.println(result);

        }
    }

}
