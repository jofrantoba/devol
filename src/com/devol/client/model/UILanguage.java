package com.devol.client.model;

import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UILanguage extends PopupPanel implements ClickHandler{
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private Button btnIngles;
	private Button btnEspanol;
	private VerticalPanel panel;
	
	public UILanguage(){
		super(true,true);
		initComponents();
		style();
		widgetListener();
	}
	
	private void initComponents(){
		panel=new VerticalPanel();
		btnIngles=new Button(constants.ingles());
		btnEspanol=new Button(constants.espanol());
		panel.add(btnIngles);
		panel.add(btnEspanol);
		this.setGlassEnabled(true);       
		this.setAnimationEnabled(true);
		//this.setText(constants.seleccioneIdioma());
		this.setWidget(panel);	
		this.center();
	}
	
	private void style(){
		panel.getElement().getStyle().setWidth(100, Unit.PCT);
		btnIngles.getElement().getStyle().setWidth(100, Unit.PCT);
		btnIngles.getElement().getStyle().setFontSize(1.5, Unit.EM);
		btnEspanol.getElement().getStyle().setWidth(100, Unit.PCT);
		btnEspanol.getElement().getStyle().setFontSize(1.5, Unit.EM);
	}
	
	private void widgetListener(){
		btnIngles.addClickHandler(this);
		btnEspanol.addClickHandler(this);
	}


	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(btnIngles)){
			Window.Location.assign("https://devolpay.appspot.com/?locale=en");
		}else if(event.getSource().equals(btnEspanol)){
			Window.Location.assign("https://devolpay.appspot.com");
		}
	}
}
