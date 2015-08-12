package com.devol.client.model;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.animation.bundle.AnimationBase;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.devol.client.resource.MyResource;
import com.devol.client.view.uihome.UIHome;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.client.view.uimenu.UIMenuImpl;

public class UIHomeHeader extends HeaderMenu implements ClickHandler {

	private PushButton btnMenu;	
	private PushButton btnAbout;
	private Label lblTitulo;
	

	public UIHomeHeader() {
		init();
		style();
	}

	private void init() {
		// TODO Auto-generated method stub
		lblTitulo=new Label("DEVOL"); 
		this.setCenterWidget(lblTitulo);
		btnMenu = new PushButton(new Image(MyResource.INSTANCE.getImgMenu32()));		
		btnMenu.setTitle("Menu Principal");
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
		UIHomeSesion.getUihomesesion().getUiMenuImpl();
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
