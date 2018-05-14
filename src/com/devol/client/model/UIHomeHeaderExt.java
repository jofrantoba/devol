package com.devol.client.model;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;

public class UIHomeHeaderExt extends HeaderMenu implements ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private PushButton btnPais;
	private PushButton btnAbout;
	private Label lblTitulo;
	

	public UIHomeHeaderExt() {
		init();
		style();
	}

	private void init() {
		// TODO Auto-generated method stub
		lblTitulo=new Label(constants.devolpay()); 
		this.setCenterWidget(lblTitulo);
		btnPais = new PushButton(new Image(MyResource.INSTANCE.getImgMundo32()));		
		btnPais.setTitle(constants.seleccioneIdioma());
		btnPais.addClickHandler(this);		
		this.setLeftWidget(btnPais);
		btnAbout = new PushButton(new Image(MyResource.INSTANCE.getImgAbout32()));		
		btnAbout.setTitle(constants.acercaDevol());
		btnAbout.addClickHandler(this);		
		this.setRightWidget(btnAbout);
	}

	private void style() {		
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		// TODO Auto-generated method stub
		this.addStyleName(MyResource.INSTANCE.getStlUIHome().headerPrincipal());
		btnPais.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
		btnAbout.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
	}

	public void setVisibleBtnPais(boolean valor) {
		this.btnPais.setVisible(valor);
	}

	public void goToUIPais() {
		// TODO Auto-generated method stub
		UILanguage uiLanguage=new UILanguage();
		uiLanguage.show();
	}

	public Label getLblTitulo() {
		return lblTitulo;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		goToUIPais();
	}
	
	

}
