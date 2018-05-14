package com.devol.client.view.uimantamortizacion;

import java.math.BigDecimal;

import com.devol.client.beanproxy.AmortizacionProxy;
import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.model.ContentForm;
import com.devol.client.model.HeaderPanelM;
import com.devol.client.model.TextBoxCalendar;
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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class UIMantAmortizacion extends Composite implements InterUIMantAmortizacion,
		ResizeHandler,TouchEndHandler,ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private HeaderPanelM header;
	private Label lblCenter;
	private PushButton btnBack;
	public ScrollPanel scrollPanel;
	private FlowPanel contenido;
	protected TextBox txtCodigo;
	protected TextBox txtMonto;
	protected TextBoxCalendar txtFecha;
	protected Button btnGuardar;
	private FlowPanel pnlForm;
	private ContentForm contentForm;
	//private FlowPanel container;
	protected String modo;	
	protected AmortizacionProxy beanAmortizacion;
	protected PrestamoProxy beanPrestamo;
	protected BigDecimal vADevolver; 
	

	public UIMantAmortizacion() {
		init();
		initWidgetListener();
		style();
	}

	private void init() {
		main = new FlowPanel();
		initWidget(main);
		Window.addResizeHandler(this);

		header = new HeaderPanelM();
		lblCenter = new Label(constants.modoNuevo());
		header.setCenterWidget(lblCenter);
		main.add(header);

		btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
		header.setLeftWidget(btnBack);

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
		//container.add(scrollPanel);

		contenido = new FlowPanel();
		scrollPanel.setWidget(contenido);

		pnlForm = new FlowPanel();
		contenido.add(pnlForm);

		contentForm = new ContentForm();
		pnlForm.add(contentForm);
		
		txtCodigo = new TextBox();

		txtMonto = new TextBox();
		contentForm.addWidget("* "+constants.monto(), txtMonto);

		txtFecha = new TextBoxCalendar();
		contentForm.addWidget("* "+constants.fecha(), txtFecha);

		btnGuardar = new Button(constants.guardar());
		//btnGuardar.setConfirm(true);
		contenido.add(btnGuardar);
		main.add(scrollPanel);		
	}		

	protected void setHeightContainer(int heightHeader) {		
		int height = Window.getClientHeight();
		scrollPanel.setHeight((height - heightHeader) + "px");
		this.scrollPanel.refresh();
	}

	private void initWidgetListener() {
		btnBack.addClickHandler(this);
		btnGuardar.addTouchEndHandler(this);
		btnGuardar.addClickHandler(this);
	}

	private void style() {
		btnGuardar.setWidth("97%");
		btnGuardar.getElement().getStyle().setMarginBottom(50, Unit.PX);
		btnGuardar.getElement().getStyle().setFontSize(2, Style.Unit.EM);
		contenido.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		MyResource.INSTANCE.getStlUIMantAmortizar().ensureInjected();
		btnBack.addStyleName(MyResource.INSTANCE.getStlUIMantAmortizar().pushButton());
		//btnGuardar.addStyleName(MyResource.INSTANCE.getStlUIMantAmortizar().pushButton());
		main.setWidth("100%");
		setHeightContainer(100);
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");		
		pnlForm.setWidth("100%");
		contenido.setWidth("100%");
		//pnlForm.getElement().getStyle().setOverflow(Overflow.HIDDEN);
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setWidthPnlFlexTable();
		setHeightContainer(100);
	}

	private void setWidthPnlFlexTable() {
		int width = Window.getClientWidth();
		width = width - 20;
		// pnlFlexTable.setWidth(width + "px");
	}
	
	public void setModo(String modo) {
		this.modo = modo;
		lblCenter.setText(modo);
	}
	
	public void setBean(AmortizacionProxy beanAmortizacion,PrestamoProxy beanPrestamo,String vADevolver) {				
		this.beanPrestamo=beanPrestamo;
		this.vADevolver=BigDecimal.valueOf(Double.valueOf(vADevolver));
		if(beanAmortizacion!=null){			
			this.beanAmortizacion=beanAmortizacion;
			llenarCampos();
		}		
	}
	
	private void llenarCampos() {
		txtCodigo.setText(beanAmortizacion.getIdPrestamo());						
		txtMonto.setText(beanAmortizacion.getMonto().toString());		
		DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha=format.format(beanAmortizacion.getFecha());
		txtFecha.getTxtFecha().setText(fecha);				
	}
	
	public void activarCampos() {
		if(modo.equalsIgnoreCase(constants.modoEliminar())){
			txtCodigo.setReadOnly(true);			
			txtMonto.setReadOnly(true);			
		}else{
			txtCodigo.setReadOnly(true);			
			txtMonto.setReadOnly(false);			
		}
		
	}

	@Override
	public void goToUIAmortizacion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrar() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void actualizarSaldos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(FieldVerifier.isEmpty(txtFecha.getText())){
			//Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtMonto.getText())){
			//Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}
		BigDecimal vMonto=BigDecimal.valueOf(Double.valueOf(txtMonto.getText()));
		if(vMonto.compareTo(vADevolver)==1 || vMonto.compareTo(BigDecimal.ZERO)==0){
			//Dialogs.alert(constants.alerta(), constants.mayorADevolver(), null);
			//Window.alert(constants.mayorADevolver());
			Notification not=new Notification(Notification.ALERT,constants.mayorADevolver());
			not.showPopup();
			return false;
		}
		return true;
	}

	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnGuardar)) {
			registrar();
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnBack)) {
			goToUIAmortizacion();
		}else if (event.getSource().equals(btnGuardar)) {
			registrar();
		}
	}

}
