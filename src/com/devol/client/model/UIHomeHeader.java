package com.devol.client.model;


import com.devol.client.resource.MyResource;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

public class UIHomeHeader extends HeaderMenu implements ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private PushButton btnMenu;	
	private PushButton btnAbout;
	private Label lblTitulo;
	

	public UIHomeHeader() {
		init();
		style();
	}

	private void init() {
		// TODO Auto-generated method stub
		lblTitulo=new Label(constants.devolpay()); 
		this.setCenterWidget(lblTitulo);
		btnMenu = new PushButton(new Image(MyResource.INSTANCE.getImgMenu32()));		
		btnMenu.setTitle(constants.menuPrincipal());
		btnMenu.addClickHandler(this);		
		this.setLeftWidget(btnMenu);
		btnAbout = new PushButton(new Image(MyResource.INSTANCE.getImgAbout32()));		
		btnAbout.setTitle("Acerca de los Desarrolladores");
		btnAbout.addClickHandler(this);		
		this.setRightWidget(btnAbout);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		// TODO Auto-generated method stub
		this.addStyleName(MyResource.INSTANCE.getStlUIHome().headerPrincipal());
		btnMenu.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
		btnAbout.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
	}

	public void setVisibleBtnMenu(boolean valor) {
		this.btnMenu.setVisible(valor);
	}

	public void goToUIMenu() {
		// TODO Auto-generated method stub
		//UIHomeSesion.animationHelper.goTo(new UIMenuImpl(),Animations.DISSOLVE);		
		UIHomeSesion.uiHomeSesion.getUiMenuImpl();
	}

	public Label getLblTitulo() {
		return lblTitulo;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		goToUIMenu();
	}
	
	

}
