package com.admin_home.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
//import com.sun.org.apache.xerces.internal.impl.xs.models.XSCMRepeatingLeaf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class AddDriver extends DialogBox implements ClickHandler {

    public static class MyDateValueChangeHandler implements ValueChangeHandler<Date> {
        private final Label text;

        public MyDateValueChangeHandler(Label text) {
            this.text = text;
        }

        public void onValueChange(ValueChangeEvent<Date> event) {
            Date date = event.getValue();
            String dateString = DateTimeFormat.getMediumDateFormat().format(date);
            text.setText(dateString);
        }

    }

    class DriverInfo {
        private long did;
        private String pass;

        DriverInfo(long did, String pass) {
            this.did = did;
            this.pass = pass;
        }

        public long getDid() {
            return did;
        }

        public String getPass() {
            return pass;
        }
    }

    VerticalPanel vpanel;
    ScrollPanel scrollPanel;
    TextBox fname, lname, mobileno, aadhar, email;

    RadioButton male, female, other;
    Button submit;
    Label flname, llname, lmobileno, laadhar, lemail, ldob,  lgender, llocation;
    private DBConnectionAsync rpc;
    //static Date d;
    DateBox dateBox;
    ListBox location;


    //CONSTRUCTOR

    AddDriver() {
        vpanel = new VerticalPanel();
        scrollPanel = new ScrollPanel(vpanel);
        fname = new TextBox();
        lname = new TextBox();
        mobileno = new TextBox();
        aadhar = new TextBox();
        email = new TextBox();
        fname = new TextBox();
        flname = new Label("FIRST NAME");
        llname = new Label("LAST NAME");
        lmobileno = new Label("MOBILE No.");
        laadhar = new Label("AADHAR No.");
        lemail = new Label("EMAIL ADDRESS");
        ldob = new Label("DATE OF BIRTH");

        lgender = new Label("GENDER");

        submit = new Button("SUBMIT");
        submit.addClickHandler(this);
        location = new ListBox();
        llocation = new Label("SELECT LOCATION");
        male = new RadioButton("radiogroup", "MALE");
        female = new RadioButton("radiogroup", "FEMALE");
        other = new RadioButton("radiogroup", "OTHER");
        setGlassEnabled(true);
        setAnimationEnabled(true);
        setText("ADD A DRIVER");

        setAutoHideEnabled(true);

        connectionEstd();
        add_to_dialog();
        setWidget(scrollPanel);

    }


    public String genderselected() {
        if (male.getValue())
            return "MALE";
        else if (female.getValue())
            return "FEMALE";
        else
            return "OTHER";
    }


    void add_to_dialog() {
        vpanel.add(flname);
        vpanel.add(fname);
        vpanel.add(llname);
        vpanel.add(lname);
        vpanel.add(lmobileno);
        vpanel.add(mobileno);
        vpanel.add(laadhar);
        vpanel.add(aadhar);
        vpanel.add(lemail);
        vpanel.add(email);
        vpanel.add(ldob);
        datedisplay();
        vpanel.add(lgender);
        vpanel.add(male);
        vpanel.add(female);
        vpanel.add(other);


        vpanel.add(llocation);
        vpanel.add(location);

        vpanel.add(submit);
       // scrollPanel.add(vpanel);

        submit.setStyleName("gwt-searchbutton");
        location.setStyleName("locationStyle");
        fname.setStyleName("textbox");
        lname.setStyleName("textbox");
        mobileno.setStyleName("textbox");
        aadhar.setStyleName("textbox");
        email.setStyleName("textbox");


        flname.setStyleName("label");
        llname.setStyleName("label");
        llocation.setStyleName("label");
        lmobileno.setStyleName("label");
        laadhar.setStyleName("label");
        ldob.setStyleName("label");
        lemail.setStyleName("label");
        lgender.setStyleName("label");
        this.setStyleName("dialog");

    }

    public void datedisplay() {

        DatePicker datePicker = new DatePicker();
        final Label text = new Label();

        // Set the value in the text box when the user selects a date
        datePicker.addValueChangeHandler(new MyDateValueChangeHandler(text));

        setGlassEnabled(true);
        // create a date picker where years and months are selectable with drop down lists and where we
        // can navigate trough the years
        DatePicker advancedDatePicker = new DatePicker();
        advancedDatePicker.setYearArrowsVisible(true);
        advancedDatePicker.setYearAndMonthDropdownVisible(true);
        // show 51 years in the years dropdown. The range of years is centered on the selected date
        advancedDatePicker.setVisibleYearCount(51);

        final Label text2 = new Label();
        text2.setStyleName("textbox");
        //text2.getElement().getStyle().setMarginTop(15, Style.Unit.PX);

        // Set the value in the text box when the user selects a date
        advancedDatePicker.addValueChangeHandler(new MyDateValueChangeHandler(text2));

        // Set the default value
        datePicker.setValue(new Date(), true);
        advancedDatePicker.setValue(new Date(), true);

        // Create a DateBox
        DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
        dateBox = new DateBox();


        dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        dateBox.getDatePicker().setYearArrowsVisible(true);
        dateBox.getDatePicker().setYearArrowsVisible(true);
        vpanel.add(dateBox);
    }

    public void connectionEstd() {
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);
        AsyncCallback<ArrayList<Location>> callback6 = new locationList<ArrayList<Location>>();
        rpc.locationList(Admin_home.getUname(), callback6);
    }

    public void onClick(ClickEvent event) {
        String f=fname.getText().trim().toUpperCase();
        String l=lname.getText().trim().toUpperCase();
        String mo=mobileno.getText().trim();
        String ad=aadhar.getText().trim();
        String em=email.getText().trim().toUpperCase();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        if(f.matches("^[0-9\\.]{1,10}$") || l.matches("^[0-9\\.]{1,10}$")){
            Window.alert("Enter valid First name and Last name");
            fname.selectAll();
            fname.setText("");
            lname.selectAll();
            lname.setText("");
            return;
        }
        else if(!em.matches(emailRegex))
        {
            Window.alert("Enter valid Email Address");
            email.selectAll();
            email.setText("");
            return;
        }
        else if(Long.parseLong(mo)<1000000000 || Long.parseLong(mo)>9999999999L){
            Window.alert("Enter valid mobile no.");
            email.selectAll();
            email.setText("");
            return;
        }
        else if(Long.parseLong(ad)<100000000000L || Long.parseLong(ad)>999999999999L){
            Window.alert("Enter valid Aadhar no");
            aadhar.selectAll();
            aadhar.setText("");
            return;
        }
        else
        {
            AsyncCallback<Driver> callback5 = new insertDriver<Driver>();
            rpc.insertInfo(fname.getText(), lname.getText(), Long.parseLong(mobileno.getText()), Long.parseLong(aadhar.getText()), dateBox.getValue(), email.getText(), genderselected(), Admin_home.getUname(), location.getSelectedItemText(), callback5);
            AddDriver.this.hide();
        }
    }

    private class insertDriver<T> implements AsyncCallback<Driver> {


        @Override
        public void onFailure(Throwable caught) {
            Window.alert("DO NOT DUPLICATE VALUE");
            System.out.println("INSERTION FAILED");
        }

        @Override
        public void onSuccess(Driver result) {
            if(result.getDid()==0000 && result.getPass().equals(""))
            {
                Window.alert("ALREADY REGISTERED");
            }
            else
            {
                DriverInfo driverInfo = new DriverInfo(result.getDid(), result.getPass());
                Window.alert("Driver ID is: " + driverInfo.getDid() + "  " + "Password is: " + driverInfo.getPass());
            }

        }
    }

    private class locationList<T> implements AsyncCallback<ArrayList<Location>> {


        @Override
        public void onFailure(Throwable caught) {

        }

        @Override
        public void onSuccess(ArrayList<Location> result) {

            for (Location locationList : result) {
                String l = locationList.getLocation();

                location.addItem(l);
            }

        }


    }
}
