package com.devol.client.view.uiregistrarusuario;

import com.devol.client.model.Form;
import com.devol.client.model.UIHomeHeaderExt;
import com.devol.client.resource.MyResource;
import com.devol.client.util.Notification;
import com.devol.i18n.DevolConstants;
import com.devol.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class UIRegistrarUsuario extends Composite implements
		InterUIRegistrarUsuario, TouchEndHandler, ResizeHandler,ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	//private FlowPanel container;
	public ScrollPanel scrollPanel;
	protected TextBox txtNombre;
	private FlowPanel pnlContenido;
	private Form widgetList;
	protected TextBox txtApellido;
	protected TextBox txtCorreo;
	protected PasswordTextBox txtClave;
	private Button btnRegistrar;
	private Button btnIniciarSesion;
	private UIHomeHeaderExt header;

	public UIRegistrarUsuario() {
		init();
		initWidgetListener();
		style();
	}

	private void init() {
		main = new FlowPanel();
		initWidget(main);
		Window.addResizeHandler(this);

		header = new UIHomeHeaderExt();
		//header.setVisibleBtnMenu(false);
		main.add(header);
		
		//container = new FlowPanel();
		//main.add(container);

		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);
		/*scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setAutoHandleResize(true);*/
		//scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());
		

		pnlContenido = new FlowPanel();
		

		widgetList = new Form();
		//widgetList.setRound(true);
		pnlContenido.add(widgetList);

		txtNombre = new TextBox();
		//txtNombre.setPlaceHolder(constants.nombres()+" (*)");
		txtNombre.getElement().setAttribute("placeholder", constants.nombres());
		widgetList.addWidget(txtNombre);

		txtApellido = new TextBox();
		//txtApellido.setPlaceHolder(constants.apellidos()+" (*)");
		txtApellido.getElement().setAttribute("placeholder", constants.apellidos());
		widgetList.addWidget(txtApellido);

		txtCorreo = new TextBox();
		//txtCorreo.setPlaceHolder(constants.correo()+" (*)");
		txtCorreo.getElement().setAttribute("placeholder", constants.correo());
		widgetList.addWidget(txtCorreo);

		txtClave = new PasswordTextBox();
		//txtClave.setPlaceHolder(constants.clave()+" (*)");
		txtClave.getElement().setAttribute("placeholder", constants.clave());
		widgetList.addWidget(txtClave);

		btnRegistrar = new Button(constants.registrar());
		//btnRegistrar.setConfirm(true);
		pnlContenido.add(btnRegistrar);
		
		btnIniciarSesion = new Button(constants.entrar());
		pnlContenido.add(btnIniciarSesion);
		scrollPanel.setWidget(pnlContenido);
		main.add(scrollPanel);

	}

	protected void cleanForm() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCorreo.setText("");
		txtClave.setText("");
	}

	private void initWidgetListener() {
		this.btnRegistrar.addTouchEndHandler(this);
		this.btnIniciarSesion.addTouchEndHandler(this);
		this.btnRegistrar.addClickHandler(this);
		this.btnIniciarSesion.addClickHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlFormulario().ensureInjected();
		setHeightContainer(50);
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		
		/*widgetList.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.widgetList());*/
		txtCorreo.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtNombre.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtApellido.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtClave.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		btnRegistrar.setWidth("97%");
		btnIniciarSesion.setWidth("97%");
		pnlContenido.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		main.getElement().getStyle().setDisplay(Style.Display.BLOCK);
		//main.getElement().getStyle().setProperty("background", "transparent");
		//main.getElement().getStyle().setBackgroundImage("url("+MyResource.INSTANCE.getWallpaper().getSafeUri().asString()+") !important");
		//pnlContenido.getElement().getStyle().setBackgroundImage("url("+MyResource.INSTANCE.getWallpaper().getSafeUri().asString()+") !important");
		btnIniciarSesion.getElement().getStyle().setMarginBottom(50, Unit.PX);
		//widgetList.getElement().getStyle().setMarginRight(30, Style.Unit.PX);
		btnIniciarSesion.getElement().getStyle().setMarginTop(10, Unit.PX);
		btnIniciarSesion.getElement().getStyle().setMarginBottom(50, Unit.PX);	
		btnIniciarSesion.getElement().getStyle().setFontSize(2, Style.Unit.EM);
		btnRegistrar.getElement().getStyle().setFontSize(2, Style.Unit.EM);
	}

	
	
	

	@Override
	public void registrar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void irIniciarSesion() {
		// TODO Auto-generated method stub
		
	}
	
	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		scrollPanel.setHeight((height - heightHeader) + "px");
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeightContainer(50);
		this.scrollPanel.refresh();
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(FieldVerifier.isEmpty(txtNombre.getText())){
			//Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtApellido.getText())){
			//Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtCorreo.getText())){
			//Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtClave.getText())){
			//Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(!FieldVerifier.isEmail(txtCorreo.getText())){
			//Dialogs.alert(constants.alerta(), constants.digiteCorreo(), null);
			//Window.alert(constants.digiteCorreo());
			Notification not=new Notification(Notification.ALERT,constants.digiteCorreo());
			not.showPopup();
			return false;
		}
		return true;
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnRegistrar)) {
			if(isValidData())
			registrar();
		}else if (event.getSource().equals(btnIniciarSesion)) {
			irIniciarSesion();
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnRegistrar)) {
			if(isValidData())
			registrar();
		}else if (event.getSource().equals(btnIniciarSesion)) {
			irIniciarSesion();
		}
	}
}
