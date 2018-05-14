package com.devol.client.util;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;

/**
 *
 * @author jofrantoba
 */
public class PopupProgress extends PopupPanel{
    private FlexTable pnlContenedor;
    private Label lblMsg;
    private ProgressBar barra;
    
    public PopupProgress(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        lblMsg=new Label("ejecutando tarea...");
        barra=new ProgressBar();
        barra.setVisible(true);
        pnlContenedor.setWidget(0, 0, lblMsg);
        pnlContenedor.setWidget(1, 0, barra);        
        this.setWidget(pnlContenedor);
    }
    
    private void initStyle(){
        pnlContenedor.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
    }
    
    public void showPopup(){
        this.setGlassEnabled(true);
        this.setAnimationEnabled(true);                
        this.setSize("20em", "5em");
        this.center();
        this.setModal(true);        
        this.show();
    }
    
    public void hidePopup(){        
            this.hide();
    }
    
}