package com.admin_home.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;

public class AddDustbin extends DialogBox implements ClickHandler {
    VerticalPanel vpanel;
    TextBox drid,cap;
    Label ldid,lcap;
    Button register;
    private DBConnectionAsync rpc;
    AddDustbin()
    {
        drid=new TextBox();
        cap=new TextBox();
        ldid=new Label("DRIVER ID");
        lcap=new Label("CAPACITY OF DUSTBIN IN LITRES");
        register=new Button("REGISTER");
        vpanel = new VerticalPanel();
        register.addClickHandler(this);
        connectionEstd();
        add_to_dialog1();
        setAnimationEnabled(true);
        setText("ADD A DUSTBIN");
        setAutoHideEnabled(true);
        setPopupPosition(800, 100);
        setWidget(vpanel);
    }
    void add_to_dialog1() {
        vpanel.add(ldid);
        vpanel.add(drid);
        vpanel.add(lcap);
        vpanel.add(cap);
        vpanel.add(register);

    }
    public void connectionEstd() {
        rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
        ServiceDefTarget target = (ServiceDefTarget) rpc;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "Postgreconnection";
        target.setServiceEntryPoint(moduleRelativeURL);

    }
    public void onClick(ClickEvent event) {
        AsyncCallback<String> callback6 = new insertDustbin<String>();
        rpc.insertDustbin(Long.parseLong(drid.getText()),Double.parseDouble(cap.getText()), callback6);
        AddDustbin.this.hide();
    }

    private class insertDustbin<T> implements AsyncCallback<String> {


        @Override
        public void onFailure(Throwable caught) {
            Window.alert("DO NOT DUPLICATE VALUE");
            System.out.println("INSERTION FAILED");
        }

        @Override
        public void onSuccess(String result) {
            if(result.equals("S"))
            Window.alert("DUSTBIN REGISTERED SUCCESSFULLY");

            else if(result.equals("E"))
            {
                Window.alert("ENTER REGISTERED DRIVER ID");
            }

        }
    }

}
