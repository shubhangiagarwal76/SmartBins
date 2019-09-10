package com.admin_home.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Admin_home implements EntryPoint {

    public void onModuleLoad() {
        // Create a tab panel with three tabs, each of which displays a different
        // piece of text.
        TabPanel tp = new TabPanel();
        tp.add(new Label("Home"), "Home");
        tp.add(new Label("Stats"), "Stats");
        tp.add(new Label("Contact"), "Contact");

        // Show the 'Home' tab initially.
        tp.selectTab(0);
        tp.setWidth("500");

        // Add it to the root panel.
        RootPanel.get().add(tp);
    }
}

