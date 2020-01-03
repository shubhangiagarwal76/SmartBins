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
//import com.google.gwt.maps.client.MapWidget;
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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.AbsolutePanel;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsType;

//@JsType(isNative = true, namespace = "mapboxgl")
//
//class Map {
//
//    @JsOverlay
//    private static long serial = 0L;
//
//    @JsConstructor
//    protected Map(JavaScriptObject options) { }
//
//    @JsOverlay
//    public static Map build(AbsolutePanel panel) {
//        return Map.build(panel, new JSONObject());
//    }
//
//    @JsOverlay
//    public static Map build(AbsolutePanel panel, JSONObject mapOptions) {
//
//        String id = "map" + serial++;
//        mapOptions.put("container", new JSONString(id));
//
//        panel.getElement().setId(id);
//        Map result = new Map(mapOptions.getJavaScriptObject());
//        return result;
//    }
//
//}
public class Admin_home  implements EntryPoint, ClickHandler{
    VerticalPanel vpanel_login;
    HorizontalPanel hpanel;
    Button login;
    static TextBox uname;
    private PasswordTextBox pwd;
    Image img;
    Anchor forget;
    private DBConnectionAsync rpc;
    User1 n;
    int count = 0;
    //Button test;
    public Admin_home() {
        this.uname = new TextBox();
        this.forget = new Anchor();
        this.pwd = new PasswordTextBox();
        this.vpanel_login = new VerticalPanel();
        this.hpanel = new HorizontalPanel();
        this.login = new Button("LOGIN");
        login.addClickHandler(this::onClick);
        this.img = new Image("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjShIDEiO3lAhVJyDgGHVf1B4IQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.flaticon.com%252Ffree-icon%252Flogin-button_16036%26psig%3DAOvVaw3VMXObzc2NQ36Ce1hK2QXF%26ust%3D1573936504671488&psig=AOvVaw3VMXObzc2NQ36Ce1hK2QXF&ust=1573936504671488");

        /*this.test=new Button("TEST",(ClickHandler) event -> {try {
            ScriptInjector.fromUrl("https://api.mapbox.com/mapbox-gl-js/v1.4.1/mapbox-gl.js")
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .setCallback(new Callback<Void, Exception>() {
                        @Override
                        public void onFailure(Exception reason) {
                            GWT.log("lodash loading failed", null);
                        }
                        @Override
                        public void onSuccess(Void result) {
                            GWT.log("lodash loaded url", null);
                        }
                    }).inject();
            GWT.log("SCRIPT INJECTED ", null);
//            ScriptInjector.fromString(
//                    "mapboxgl.accessToken ='pk.eyJ1IjoicHJhbmt1ciIsImEiOiJjazBlZW9tZDkwMDhjM3B1ZzJtdWgwY2lzIn0.JYo2k03E6aW3f4UYp0Feww';\n" +
//                    "var map = new mapboxgl.Map({" +
//                    "container: 'map'," +
//                    "style: 'mapbox://styles/mapbox/satellite-v9'," +
//                    "center: [77.362423,28.619753]," +
//                    "zoom:18" +
//
                    ScriptInjector.fromUrl("http://mohammadnoman.co/temp.js")
                    .setWindow(ScriptInjector.TOP_WINDOW)
                    .setCallback(new Callback<Void, Exception>() {
                        @Override
                        public void onFailure(Exception reason) {
                            GWT.log("lodash loading failed", null);
                        }

                        @Override
                        public void onSuccess(Void result) {
                            GWT.log("lodash 2 loaded", null);
                        }
                    }).inject();
            //mapLoad();
            GWT.log("RUN MAPLOAD ", null);
        } catch (Exception e) {
            GWT.log("JSNI method badExample() threw an exception:", e);
        }});*/
        /*login.getElement().getStyle().setBackgroundImage("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjShIDEiO3lAhVJyDgGHVf1B4IQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.flaticon.com%252Ffree-icon%252Flogin-button_16036%26psig%3DAOvVaw3VMXObzc2NQ36Ce1hK2QXF%26ust%3D1573936504671488&psig=AOvVaw3VMXObzc2NQ36Ce1hK2QXF&ust=1573936504671488");
        vpanel.add(uname);
        uname.setName("UserName");
        vpanel.add(pwd);
        pwd.setName("Password");
        hpanel.add(login);
        hpanel.add(forget);
        vpanel.add(hpanel);
        //vpanel.add(test);
        hpanel.setSpacing(10);
        vpanel.setStyleName("Vertical");
        forget.setHref("LOGIN.html");
        forget.setText("Forget Password?");
        forget.setSize("2","2");
        vpanel.setBorderWidth(3);*/
    }

    //Event handled at login
    public void onClick(ClickEvent event)
    {

        //client side verification
        if(event.getSource()!=null)
        {
        String u=uname.getText().toUpperCase().trim();
        if (!u.matches("^[0-9\\.]{1,10}$")) {
            Window.alert("'" + u + "' is not a valid symbol.");
            uname.selectAll();
            uname.setText("");
            pwd.selectAll();
            pwd.setText("");
        }
        //server side verification
        else
        {
            connection();
        }}
    }

    //getter for textbox
    public static long getUname(){
        return Long.parseLong(uname.getText());
    }

    private static class User1 {
        private long name;
        private String pass;

        private int n;
        public User1(){

        }
        public User1(long name, String pass, int n)
        {
            this.name = name;
            this.pass=pass;
            this.n=n;
        }
        public long getName()
        {
            return name;
        }
        public  String getPass()
        {
            return pass;
        }

        public int getN() {
            return n;
        }
        public void setN(int n)
        {
            this.n = n;
        }
    }
    public void connection()
    {
        //int n;
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);
        long uname=Long.parseLong(this.uname.getText());
        String pass=this.pwd.getText();
        AsyncCallback<User> callback3 = new AuthenticationHandlers<User>();
        rpc.authenticateUser(uname,pass,-1,callback3);

    }
    private class AuthenticationHandlers<T> implements AsyncCallback<User>
    {

        public void onFailure(Throwable ex) {
            HTML h = new HTML("RPC unsuccessful");
            RootPanel.get().add(h);
        }
        public void onSuccess(User result)
        {
            n = new User1();
            int a=result.getValue();
            Integer aa=a;
            String temp=aa.toString();
            //RootPanel.get().add(new Label(temp));
            n.setN (result.getValue());
            if(n.getN()==1) {
                RootPanel.get().clear();
                vpanel_login.clear();
                System.out.println("VALUE OF N"+n.getN());
                admin_dashboard ad = new admin_dashboard();
                ad.onModuleLoad();
            }
            else if(n.getN()==0)
            {
                Window.alert("Invalid Password");
                System.out.println("VALUE OF N"+n.getN());
            }
            else if (n.getN()==-1)
            {
                Window.alert("Invalid Username");
                System.out.println("VALUE OF N"+n.getN());
            }

           /* while (count!=1) {
                connection();
                count++;*/


        }
    }




    /*public static native void mapLoad()
        *//*-{
            $wnd.alert("HELP ME PLZ");
            mapboxgl.accessToken ='pk.eyJ1IjoicHJhbmt1ciIsImEiOiJjazBlZW9tZDkwMDhjM3B1ZzJtdWgwY2lzIn0.JYo2k03E6aW3f4UYp0Feww';
                var map = new mapboxgl.Map({
                   container: 'map',
                   style: 'mapbox://styles/mapbox/satellite-v9',
                  center: [77.362423,28.619753],
                    zoom:18
                });
        }-*//*;*/
    public void addingpanel(){
        login.getElement().getStyle().setBackgroundImage("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwjShIDEiO3lAhVJyDgGHVf1B4IQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.flaticon.com%252Ffree-icon%252Flogin-button_16036%26psig%3DAOvVaw3VMXObzc2NQ36Ce1hK2QXF%26ust%3D1573936504671488&psig=AOvVaw3VMXObzc2NQ36Ce1hK2QXF&ust=1573936504671488");
        vpanel_login.add(uname);
        uname.setName("UserName");
        vpanel_login.add(pwd);
        pwd.setName("Password");
        hpanel.add(login);
        hpanel.add(forget);
        vpanel_login.add(hpanel);
        hpanel.setSpacing(10);
        vpanel_login.setStyleName("Vertical");
        forget.setHref("LOGIN.html");
        forget.setText("Forget Password?");
        forget.setSize("2","2");
        vpanel_login.setBorderWidth(3);

    }
    public void onModuleLoad() {
        addingpanel();
        RootPanel.get().add(vpanel_login);
//        HorizontalPanel horizontalPanel = new HorizontalPanel();
//        horizontalPanel.setHeight("400px");
//        horizontalPanel.setWidth("600px");
//        horizontalPanel.getElement().setId("geo-map");
//
// ScriptInjector.fromUrl("https://api.mapbox.com/mapbox-gl-js/v1.4.1/mapbox-gl.js").setCallback(new Callback<Void, Exception>() {

//            @Override
//            public void onFailure(Exception reason) {
//
//                Label l1=new Label("NOT WORKING MAPS");
//                        horizontalPanel.add(l1);
//                        RootPanel.get().add(horizontalPanel);
//
//            }
//
//            @Override
//            public void onSuccess(Void result) {
//
//                ScriptInjector.fromString("$wnd.$(document).ready(function() {" +
//                        "console.log(\"Ok, it's me!\");" +
//                        "L.mapbox.accessToken = \"pk.eyJ1IjoicHJhbmt1ciIsImEiOiJjazBlZW9tZDkwMDhjM3B1ZzJtdWgwY2lzIn0.JYo2k03E6aW3f4UYp0Feww\";" +
//                        "var map = L.mapbox.map(\"mapbox://styles/mapbox/streets-v10\", \"geo-map\").setView([40, -74.50], 9);" +
//                        "console.log(\"Ok, it's me again!\");" +
//                        "});").inject();
//
//            }
//        }).inject();
//
//        // Add the nameField and sendButton to the RootPanel
//        // Use RootPanel.get() to get the entire body element
//        RootPanel.get().add(horizontalPanel);
////        JSONObject mapOptions = new JSONObject();
//        JSONArray center = new JSONArray();
//        center.set(0, new JSONNumber(-122.420679));
//        center.set(1, new JSONNumber(37.772537));
//        mapOptions.put("style", new JSONString("mapbox://styles/mapbox/streets-v9"));
//        mapOptions.put("zoom", new JSONNumber(13));
//        mapOptions.put("center", center);
//        MapPanel mapPanel = new MapPanel(300,300, mapOptions);
//        RootPanel.get().add(mapPanel);

    }}