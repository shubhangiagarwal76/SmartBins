package com.admin_home.client;

import com.admin_home.server.Postgreconnection;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
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

public class Admin_home implements EntryPoint{
    VerticalPanel vpanel;
    HorizontalPanel hpanel;
    Button login;
    TextBox uname;
    private PasswordTextBox pwd;
    Image img;
    Anchor forget;
    public Admin_home() {
        this.uname = new TextBox();
        this.forget = new Anchor();
        this.pwd = new PasswordTextBox();
        this.vpanel = new VerticalPanel();
        this.hpanel = new HorizontalPanel();
        this.login = new Button("LOGIN", (ClickHandler) event -> {
            String u=uname.getText().toUpperCase().trim();
            if (!u.matches("^[0-9A-Z.]{1,10}$")) {
                Window.alert("'" + u + "' is not a valid symbol.");
                uname.selectAll();
                uname.setText("");
                pwd.selectAll();
                pwd.setText("");
                return;
            }
            else
                { vpanel.clear();
            admin_dashboard ad = new admin_dashboard();
            ad.onModuleLoad();

        }});
        this.img = new Image("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjShIDEiO3lAhVJyDgGHVf1B4IQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.flaticon.com%252Ffree-icon%252Flogin-button_16036%26psig%3DAOvVaw3VMXObzc2NQ36Ce1hK2QXF%26ust%3D1573936504671488&psig=AOvVaw3VMXObzc2NQ36Ce1hK2QXF&ust=1573936504671488");
        login.getElement().getStyle().setBackgroundImage("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjShIDEiO3lAhVJyDgGHVf1B4IQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.flaticon.com%252Ffree-icon%252Flogin-button_16036%26psig%3DAOvVaw3VMXObzc2NQ36Ce1hK2QXF%26ust%3D1573936504671488&psig=AOvVaw3VMXObzc2NQ36Ce1hK2QXF&ust=1573936504671488");
        vpanel.add(uname);
        uname.setName("UserName");
        vpanel.add(pwd);
        pwd.setName("Password");
        hpanel.add(login);
        hpanel.add(forget);
        vpanel.add(hpanel);
        hpanel.setSpacing(10);
        vpanel.setStyleName("Vertical");
        forget.setHref("LOGIN.jsp");
        forget.setText("Forget Password?");
        forget.setSize("2","2");
        vpanel.setBorderWidth(3);



    }
    public void onModuleLoad() {
        RootPanel.get().add(vpanel);

    }}