package com.devol.client.view.uirecuperarclave;

import com.devol.client.model.Form;
import com.devol.client.model.UIHomeHeaderExt;
import com.devol.client.resource.MyResource;
import com.devol.client.util.Notification;
import com.devol.i18n.DevolConstants;
import com.devol.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.TextDecoration;
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
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class UiRecuperarClave extends Composite
		implements InterUiRecuperarClave, TouchEndHandler, ResizeHandler, ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	// private FlowPanel container;
	public ScrollPanel scrollPanel;
	private FlowPanel pnlContenido;
	private Form widgetList;
	protected TextBox txtCorreo;
	private Button btnRegistrar;
	private Button btnIniciarSesion;
	private UIHomeHeaderExt header;

	public UiRecuperarClave() {
		init();
		initWidgetListener();
		style();
	}

	private void init() {
		main = new FlowPanel();
		initWidget(main);
		Window.addResizeHandler(this);

		header = new UIHomeHeaderExt();
		// header.setVisibleBtnMenu(false);
		main.add(header);

		// container = new FlowPanel();
		// main.add(container);

		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);
		/*
		 * scrollPanel.setScrollingEnabledX(false);
		 * scrollPanel.setScrollingEnabledY(true);
		 * scrollPanel.setAutoHandleResize(true);
		 */
		// scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());

		pnlContenido = new FlowPanel();

		widgetList = new Form();
		// widgetList.setRound(true);
		pnlContenido.add(widgetList);

		txtCorreo = new TextBox();
		// txtCorreo.setPlaceHolder(constants.correo()+" (*)");
		txtCorreo.getElement().setAttribute("placeholder", constants.correo());
		widgetList.addWidget(txtCorreo);

		btnRegistrar = new Button("Recuperar clave");
		// btnRegistrar.setConfirm(true);
		pnlContenido.add(btnRegistrar);

		btnIniciarSesion = new Button(constants.entrar());
		pnlContenido.add(btnIniciarSesion);
		scrollPanel.setWidget(pnlContenido);
		main.add(scrollPanel);

	}

	protected void cleanForm() {		
		txtCorreo.setText("");		
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
		// scrollPanel.setWidth("100%");
		// scrollPanel.setHeight("100%");

		/*
		 * widgetList.addStyleName(MyResource.INSTANCE.getStlFormulario()
		 * .widgetList());
		 */
		txtCorreo.addStyleName(MyResource.INSTANCE.getStlFormulario().textBox());		
		btnRegistrar.setWidth("97%");
		btnIniciarSesion.setWidth("97%");
		pnlContenido.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		main.getElement().getStyle().setDisplay(Style.Display.BLOCK);
		// main.getElement().getStyle().setProperty("background",
		// "transparent");
		// main.getElement().getStyle().setBackgroundImage("url("+MyResource.INSTANCE.getWallpaper().getSafeUri().asString()+")
		// !important");
		// pnlContenido.getElement().getStyle().setBackgroundImage("url("+MyResource.INSTANCE.getWallpaper().getSafeUri().asString()+")
		// !important");
		btnIniciarSesion.getElement().getStyle().setMarginBottom(50, Unit.PX);
		// widgetList.getElement().getStyle().setMarginRight(30, Style.Unit.PX);
		btnIniciarSesion.getElement().getStyle().setMarginTop(10, Unit.PX);
		btnIniciarSesion.getElement().getStyle().setMarginBottom(50, Unit.PX);
		btnIniciarSesion.getElement().getStyle().setFontSize(2, Style.Unit.EM);
		btnRegistrar.getElement().getStyle().setFontSize(2, Style.Unit.EM);
		btnIniciarSesion.getElement().removeAttribute("class");
		btnIniciarSesion.getElement().getStyle().setBackgroundColor("transparent");
		btnIniciarSesion.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
		btnIniciarSesion.getElement().getStyle().setTextDecoration(TextDecoration.UNDERLINE);
		btnIniciarSesion.getElement().getStyle().setColor("gray");
		btnIniciarSesion.getElement().getStyle().setCursor(Style.Cursor.POINTER);
	}

	@Override
	public void recuperarClave() {
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
		if (FieldVerifier.isEmpty(txtCorreo.getText())) {
			// Dialogs.alert(constants.alerta(), constants.camposObligatorios(),
			// null);
			// Window.alert(constants.camposObligatorios());
			Notification not = new Notification(Notification.ALERT, constants.camposObligatorios());
			not.showPopup();
			return false;		
		} else if (!FieldVerifier.isEmail(txtCorreo.getText())) {
			// Dialogs.alert(constants.alerta(), constants.digiteCorreo(),
			// null);
			// Window.alert(constants.digiteCorreo());
			Notification not = new Notification(Notification.ALERT, constants.digiteCorreo());
			not.showPopup();
			return false;
		}
		return true;
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnRegistrar)) {
			if (isValidData())
				recuperarClave();
		} else if (event.getSource().equals(btnIniciarSesion)) {
			irIniciarSesion();
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnRegistrar)) {
			if (isValidData())
				recuperarClave();
		} else if (event.getSource().equals(btnIniciarSesion)) {
			irIniciarSesion();
		}
	}
}
