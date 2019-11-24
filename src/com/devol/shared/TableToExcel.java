package com.devol.shared;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author SISTEMAS
 */
public class TableToExcel {
    public static final void save(final Widget table, String filename) {
        final AnchorElement a = Document.get().createAnchorElement();         
        a.setHref("data:application/vnd.ms-excel;base64," + base64(table.getElement().getString()));        
        a.setPropertyString("download", (filename.endsWith(".xls") || filename.endsWith(".xlsx")) ? filename : filename + ".xls");        

        Document.get().getBody().appendChild(a);
        Scheduler.get().scheduleEntry(new ScheduledCommand() {
            @Override
            public void execute() {
                click(a);
                a.removeFromParent();
            }
        });
    }

    private static native void click(Element elem) /*-{
        elem.click();
    }-*/;

    public static native String base64(String data) /*-{
        return btoa(data);
    }-*/;
}