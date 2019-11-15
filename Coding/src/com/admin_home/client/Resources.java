package com.admin_home.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.cellview.client.CellTable;

public interface Resources extends CellTable.Resources {
    public interface CellTableStyle extends CellTable.Style {
    };

    @Source({"Admin_home.css"})

    CellTableStyle cellTableStyle();
}