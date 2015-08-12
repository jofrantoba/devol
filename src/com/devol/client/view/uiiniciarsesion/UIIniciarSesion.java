package com.devol.client.view.uiiniciarsesion;

import com.devol.client.model.ContentForm;
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

public class UIIniciarSesion extends Composite implements InterUIIniciarSesion,
TouchEndHandler, ResizeHandler,ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private UIHomeHeaderExt header;
	public ScrollPanel scrollPanel;
	private FlowPanel content;
	private Form widgetList;
	protected TextBox txtCorreo;
	protected PasswordTextBox txtClave;
	protected Button btnEntrar;
	private Button btnCrearCuenta;
	//private FlowPanel container;

	public UIIniciarSesion(){
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
		//scrollPanel.setScrollingEnabledX(false);
		//scrollPanel.setAutoHandleResize(true);
		//scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());		
		
		content = new FlowPanel();
		
		
		widgetList = new Form();
		//widgetList.setRound(true);
		content.add(widgetList);
		
		txtCorreo = new TextBox();
		//txtCorreo.setPlaceHolder(constants.correo());
		txtCorreo.getElement().setAttribute("placeholder", constants.correo());
		widgetList.addWidget(txtCorreo);
		
		txtClave = new PasswordTextBox();
		//txtClave.setPlaceHolder(constants.clave());
		txtClave.getElement().setAttribute("placeholder", constants.clave());
		widgetList.addWidget(txtClave);
		
		btnEntrar = new Button(constants.entrar());
		//btnEntrar.setConfirm(true);		
		content.add(btnEntrar);
		
		btnCrearCuenta = new Button(constants.crearCuenta());
		content.add(btnCrearCuenta);
		scrollPanel.setWidget(content);
		main.add(scrollPanel);
	}

	private void initWidgetListener() {
		btnEntrar.addTouchEndHandler(this);
		btnCrearCuenta.addTouchEndHandler(this);
		btnEntrar.addClickHandler(this);
		btnCrearCuenta.addClickHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlFormulario().ensureInjected();
		//main.setWidth("100%");
		//container.setWidth("100%");
		setHeightContainer(50);
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		//widgetList.setWidth("100");
		/*widgetList.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.widgetList());*/
		txtCorreo.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtClave.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		content.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		btnEntrar.setWidth("97%");		
		btnCrearCuenta.setWidth("97%");
		main.getElement().getStyle().setDisplay(Style.Display.BLOCK);		
		btnCrearCuenta.getElement().getStyle().setMarginTop(10, Unit.PX);
		btnCrearCuenta.getElement().getStyle().setMarginBottom(50, Unit.PX);	
		btnCrearCuenta.getElement().getStyle().setFontSize(2, Style.Unit.EM);
		btnEntrar.getElement().getStyle().setFontSize(2, Style.Unit.EM);
		//widgetList.getElement().getStyle().setMarginRight(30, Style.Unit.PX);
	}
	
	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		scrollPanel.setHeight((height - heightHeader) + "px");
		this.scrollPanel.refresh();
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void irCuenta() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void irCrearCuenta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeightContainer(50);
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(FieldVerifier.isEmpty(txtCorreo.getText())){
			//Dialogs.alert(constants.alerta(), constants.usuarioClaveIncorrecto(), null);
			//Window.alert(constants.usuarioClaveIncorrecto());
			Notification not=new Notification(Notification.ALERT,constants.usuarioClaveIncorrecto());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtClave.getText())){
			//Dialogs.alert(constants.alerta(), constants.usuarioClaveIncorrecto(), null);
			//Window.alert(constants.usuarioClaveIncorrecto());
			Notification not=new Notification(Notification.ALERT,constants.usuarioClaveIncorrecto());
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
		if (event.getSource().equals(btnEntrar)) {
			if(isValidData())
			login();
		}else if (event.getSource().equals(btnCrearCuenta)) {
			irCrearCuenta();
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnEntrar)) {
			if(isValidData())
			login();
		}else if (event.getSource().equals(btnCrearCuenta)) {
			irCrearCuenta();
		}
	}
}
