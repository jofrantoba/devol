package com.devol.client.util;

import com.devol.client.resource.MyResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.dom.client.Style;

public class Notification extends PopupPanel{
    public final static String ALERT="ALERT";
    public final static String WARNING="WARNING";
    public final static String INFORMATION="INFORMATION";
    private FlexTable pnlContenedor;
    private Image img;
    private HTML lblMsg;
    private Timer timer;
    //private Button btnOper;
    
    public Notification(String typeNotification,String msg){
        initComponents();
        initStyle();
        lblMsg.setHTML(msg);
        if(typeNotification.equals(ALERT)){
            img.setResource(MyResource.INSTANCE.getImgAlert32());
        }else if(typeNotification.equals(WARNING)){
            img.setResource(MyResource.INSTANCE.getImgWarning32());
        }else if(typeNotification.equals(INFORMATION)){
            img.setResource(MyResource.INSTANCE.getImgInformation32());
        }        
    }
    
    public Notification(ImageResource imageResource,String msg){
        initComponents();
        initStyle();
        lblMsg.setHTML(msg);
        img.setResource(imageResource);
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        img=new Image();
        lblMsg=new HTML("Mensaje");
        //btnOper=new Button("Aceptar");
        pnlContenedor.setWidget(0, 0, img);
        pnlContenedor.setWidget(0, 1, lblMsg);
        //pnlContenedor.setWidget(1, 0, btnOper);     
        FlexTable.FlexCellFormatter cellFormatter = pnlContenedor.getFlexCellFormatter();
        //cellFormatter.setColSpan(1, 0, 2);
        cellFormatter.getElement(0, 0).getStyle().setPadding(10, Style.Unit.PX);
        cellFormatter.getElement(0, 1).getStyle().setPadding(10, Style.Unit.PX);
        //cellFormatter.getElement(1, 0).getStyle().setPadding(10, Style.Unit.PX);
        this.setWidget(pnlContenedor);
    }
    
    private void initStyle(){
        //btnOper.getElement().setId("btnOper");
        pnlContenedor.getElement().setId("pnlContenedor");
        this.getElement().setId("popup");   
        MyResource.INSTANCE.getStlNotification().ensureInjected();
             
        //pnlContenedor.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        //btnOper.setWidth("12em");        
        //this.getElement().getStyle().setBackgroundColor("red");
        //this.getElement().getStyle().setBorderWidth(0, Style.Unit.PX);  
         //this.getElement().getStyle().setProperty("opacity", "0.8");
         //this.getElement().getStyle().setProperty("box-shadow", "10px 10px 5px #888888");
    }
    
    public void showPopup(){
        //this.setGlassEnabled(true);
        this.setAnimationEnabled(true);                
        //this.setSize("100%", "100%");
        this.center();
        this.setAutoHideEnabled(true);
        this.setModal(false);        
        this.show();
        timer=new Timer(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				hidePopup();
			}			
		};
		timer.scheduleRepeating(5000);
    }
    
    public void hidePopup(){        
            this.hide();
    }
    
}
