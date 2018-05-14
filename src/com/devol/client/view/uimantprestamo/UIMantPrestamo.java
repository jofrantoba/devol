package com.devol.client.view.uimantprestamo;

import java.math.BigDecimal;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.beanproxy.UsuarioProxy;
import com.devol.client.model.ContentForm;
import com.devol.client.model.HeaderPanelM;
import com.devol.client.model.TextBoxCalendar;
import com.devol.client.resource.MyResource;
import com.devol.client.util.Notification;
import com.devol.i18n.DevolConstants;
import com.devol.shared.FieldVerifier;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class UIMantPrestamo extends Composite implements InterUIMantPrestamo,
		 TouchEndHandler,KeyUpHandler,BlurHandler,ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private HeaderPanelM header;
	private Label lblCenter;
	private PushButton btnBack;
	//private FlowPanel container;
	public ScrollPanel scrollPanel;
	private FlowPanel contenido;
	private FlowPanel pnlForm;
	private ContentForm contentForm;
	protected TextBox txtCodigo;
	protected TextBoxCalendar txtFecha;
	protected TextBox txtMonto;
	protected TextBox txtTasa;
	protected TextBox txtADevolver; 
	protected TextBox txtDevuelto;
	protected TextBox txtCliente;
	protected Button btnGuardar;
	private FlowPanel pnlTxtCliente;
	//private FlowPanel pnlTxtCalendar;
	private PushButton btnClienteAdd;
	//private ButtonBarButtonBase btnCalendarAdd;
	private FlexTable flexTable;
	//private FlexTable flexTableCalendar;
	protected ClienteProxy beanCliente;
	protected PrestamoProxy beanPrestamo;
	//private UIClienteSelect dialogCliente;
	protected String modo;
	protected String modoPrestamo;

	public UIMantPrestamo() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {
		beanCliente=null;
		main = new FlowPanel();
		initWidget(main);
		//Window.addResizeHandler(this);

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
		

		contenido = new FlowPanel();
		scrollPanel.setWidget(contenido);

		pnlForm = new FlowPanel();
		contenido.add(pnlForm);

		contentForm = new ContentForm();
		pnlForm.add(contentForm);

		txtCodigo = new TextBox();
		//contentForm.addWidget("Codigo", txtCodigo);
		//pnlTxtCalendar = new FlowPanel();
		txtFecha = new TextBoxCalendar();
		contentForm.addWidget("* "+constants.fecha(), txtFecha);
		//flexTableCalendar = new FlexTable();
		//pnlTxtCalendar.add(flexTableCalendar);
		//flexTableCalendar.setWidget(0, 0, txtFecha);
		//btnCalendarAdd = new ButtonBarButtonBase(
		//		MyResource.INSTANCE.getImgCalendar32());
		//flexTableCalendar.setWidget(0, 1, btnCalendarAdd);

		txtMonto = new TextBox();
		contentForm.addWidget("* "+constants.monto(), txtMonto);

		txtTasa = new TextBox();
		contentForm.addWidget("* "+constants.tasa()+"(%) ", txtTasa);

		txtADevolver = new TextBox();
		
		contentForm.addWidget(constants.aDevolver(), txtADevolver);

		txtDevuelto = new TextBox();
		
		contentForm.addWidget(constants.devuelto(), txtDevuelto);

		pnlTxtCliente = new FlowPanel();
		
		contentForm.addWidget("* "+constants.clientes(), pnlTxtCliente);

		flexTable = new FlexTable();		
		pnlTxtCliente.add(flexTable);

		txtCliente = new TextBox();
		txtCliente.setReadOnly(true);
		flexTable.setWidget(0, 0, txtCliente);

		btnClienteAdd = new PushButton(
				new Image(MyResource.INSTANCE.getImgClieAdd32()));		
		flexTable.setWidget(0, 1, btnClienteAdd);

		btnGuardar = new Button(constants.guardar());
		//btnGuardar.setConfirm(true);
		contenido.add(btnGuardar);
		scrollPanel.setWidget(contenido);
		main.add(scrollPanel);
		Window.addResizeHandler(new ResizeHandler(){

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}});
	}
	
	private void reCalcularWindows(){
		//setWidthGrid();
		setHeightContainer(100);		
		setWidthPnlFlexTable();
	}
	
	private void setWidthPnlFlexTable() {
		int width = Window.getClientWidth();
		width = width - 20;
		// pnlForm.setWidth(width + "px");
	}
	
	public void setModo(String modo) {
		this.modo = modo;
		lblCenter.setText(modo);
	}
	
	public void setBean(PrestamoProxy bean) {
		this.beanPrestamo = bean;
		llenarCampos();
	}
	
	private void llenarCampos() {
		txtCodigo.setText(beanPrestamo.getIdPrestamo());		
		txtADevolver.setText(beanPrestamo.getaDevolver().toString());
		txtDevuelto.setText(beanPrestamo.getDevuelto().toString());
		txtMonto.setText(beanPrestamo.getMonto().toString());
		txtTasa.setText(beanPrestamo.getTasa().toString());
		DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
		String fecha=format.format(beanPrestamo.getFecha());
		txtFecha.getTxtFecha().setText(fecha);
		txtCliente.setText(beanPrestamo.getBeanCliente().getNombre()+" "+beanPrestamo.getBeanCliente().getApellido());
		beanCliente=beanPrestamo.getBeanCliente();
	}
	
	public void activarCampos() {
		if(modo.equalsIgnoreCase(constants.modoEliminar())){
			txtCodigo.setReadOnly(true);
			txtADevolver.setReadOnly(true);
			txtDevuelto.setReadOnly(true);
			txtMonto.setReadOnly(true);
			txtTasa.setReadOnly(true);		
			btnGuardar.setVisible(true);			
		}else if(modo.equalsIgnoreCase(constants.modoEditar())){
			txtCodigo.setReadOnly(true);
			txtADevolver.setReadOnly(true);
			txtDevuelto.setReadOnly(true);
			txtMonto.setReadOnly(false);
			txtTasa.setReadOnly(false);
			if(modoPrestamo.equals("HISTORIAL")){
				btnGuardar.setVisible(false);			
			}else{
				btnGuardar.setVisible(true);
			}
		}else{
			txtCodigo.setReadOnly(true);
			txtADevolver.setReadOnly(true);
			txtDevuelto.setReadOnly(true);
			txtMonto.setReadOnly(false);
			txtTasa.setReadOnly(false);
			if(modoPrestamo.equals("HISTORIAL")){
				btnGuardar.setVisible(false);			
			}else{
				btnGuardar.setVisible(true);
			}
		}
		
	}

	private void initWidgetListener() {
		btnClienteAdd.addClickHandler(this);
		btnBack.addClickHandler(this);
		txtMonto.addKeyUpHandler(this);
		txtMonto.addBlurHandler(this);		
		txtTasa.addKeyUpHandler(this);
		txtTasa.addBlurHandler(this);
		btnGuardar.addTouchEndHandler(this);
		btnGuardar.addClickHandler(this);
	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		scrollPanel.setHeight((height - heightHeader) + "px");
		this.scrollPanel.refresh();
	}

	private void style() {
		btnGuardar.setWidth("97%");
		btnGuardar.getElement().getStyle().setMarginBottom(50, Unit.PX);
		btnGuardar.getElement().getStyle().setFontSize(2, Style.Unit.EM);
		contenido.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		MyResource.INSTANCE.getStlModel().ensureInjected();
		btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
		//main.setWidth("100%");
		//container.setWidth("100%");
		//setHeightContainer(41);
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		pnlForm.setWidth("100%");
		contenido.setWidth("100%");
		//pnlForm.getElement().getStyle().setOverflow(Overflow.HIDDEN);

		flexTable.setCellPadding(0);
		flexTable.setCellSpacing(0);
		flexTable.setWidth("100%");
		txtCliente.setWidth("100%");
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		cellFormatter.setWidth(0, 1, "40");

		flexTable.addStyleName(MyResource.INSTANCE.getStlModel()
				.flexTableTextBoxCalendar());
		txtCliente.addStyleName(MyResource.INSTANCE.getStlModel()
				.textBoxFechaTextBoxCalendar());
		btnClienteAdd.setHeight("25px");
		//btnClienteAdd.getElement().getStyle().setBackgroundColor("#0C0FB8");
		
		/*flexTableCalendar.setCellPadding(0);
		flexTableCalendar.setCellSpacing(0);
		flexTableCalendar.setWidth("100%");
		txtFecha.setWidth("100%");
		FlexCellFormatter cellFormatterCal = flexTableCalendar.getFlexCellFormatter();
		cellFormatterCal.setWidth(0, 1, "40");

		flexTableCalendar.addStyleName(MyResource.INSTANCE.getStlModel()
				.flexTableTextBoxCalendar());
		txtFecha.addStyleName(MyResource.INSTANCE.getStlModel()
				.textBoxFechaTextBoxCalendar());
		btnCalendarAdd.setHeight("32px");*/
		//btnCalendarAdd.getElement().getStyle().setBackgroundColor("#0C0FB8");
	}

	/*@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeightContainer(41);
		setWidthDialog();
	}*/

	
	
	/*public void setWidthDialog(){
		int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		dialogCliente.setWidth((width-60)+"px");
		dialogCliente.setHeight((height-80)+"px");
	}*/
	
	
	public void setBeanCliente(ClienteProxy bean){
		beanCliente = bean;
		txtCliente.setText(beanCliente.getNombre()+" "+beanCliente.getApellido());
	}
	
	@Override
	public void goToUIPrestamo() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void registrar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void goToUIClienteAdd() {
		// TODO Auto-generated method stub
		
	}
	
	protected void calcularMontos(){
		String monto=txtMonto.getText();
		String tasa=txtTasa.getText();
		String devuelto=txtDevuelto.getText();
		BigDecimal vMonto=BigDecimal.ZERO;
		BigDecimal vTasa=BigDecimal.ZERO;
		BigDecimal vAdevolver=BigDecimal.ZERO;
		BigDecimal vDevuelto=BigDecimal.ZERO;			
		if(monto!=null&&!monto.isEmpty()){
			try{
				if(modo.equalsIgnoreCase(constants.modoNuevo())){
					vMonto=BigDecimal.valueOf(Double.parseDouble(monto));
				}else if(modo.equalsIgnoreCase(constants.modoEditar())){
					vMonto=BigDecimal.valueOf(Double.parseDouble(monto));
					vDevuelto=BigDecimal.valueOf(Double.parseDouble(devuelto));
					if(vMonto.compareTo(vDevuelto)<0){
						vMonto=BigDecimal.valueOf(beanPrestamo.getMonto());
						txtMonto.setText(vMonto.toString());
					}
				}									
			}catch(Exception ex){	
				vMonto=BigDecimal.ZERO;
				txtMonto.setText(BigDecimal.ZERO.toString());
			}
		}else{
			vMonto=BigDecimal.ZERO;			
			txtMonto.setText(vMonto.toString());
		}
		if(tasa!=null&&!tasa.isEmpty()){
			try{
				vTasa=BigDecimal.valueOf(Double.parseDouble(tasa));				
			}catch(Exception ex){								
				vTasa=BigDecimal.ZERO;
				txtTasa.setText(vTasa.toString());
			}
		}else{
			vTasa=BigDecimal.ZERO;
			txtTasa.setText(vTasa.toString());			
		}
		if(devuelto!=null&&!devuelto.isEmpty()){
			try{
				vDevuelto=BigDecimal.valueOf(Double.parseDouble(devuelto));				
			}catch(Exception ex){								
				vDevuelto=BigDecimal.ZERO;
				txtDevuelto.setText(vDevuelto.toString());
			}
		}else{
			vDevuelto=BigDecimal.ZERO;
			txtDevuelto.setText(vDevuelto.toString());			
		}					
			vAdevolver=vMonto.add(vTasa.divide(BigDecimal.valueOf(100)).multiply(vMonto)).subtract(vDevuelto);
			txtADevolver.setText(vAdevolver.toString());
			txtDevuelto.setText(vDevuelto.toString());		
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(txtMonto)||event.getSource().equals(txtTasa))
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			calcularMontos();
		}
	}

	@Override
	public void onBlur(BlurEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(txtMonto)||event.getSource().equals(txtTasa))			
				calcularMontos();
			
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(!modo.equalsIgnoreCase(constants.modoEliminar())){
		if(FieldVerifier.isEmpty(txtFecha.getText())){
			//Dialogs.alert("Alerta", "Campos con  (*) son obligatorios", null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtMonto.getText())){
			//Dialogs.alert("Alerta", "Campos con  (*) son obligatorios", null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtTasa.getText())){
			//Dialogs.alert("Alerta", "Campos con  (*) son obligatorios", null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}else if(FieldVerifier.isEmpty(txtCliente.getText())){
			//Dialogs.alert("Alerta", "Campos con  (*) son obligatorios", null);
			//Window.alert(constants.camposObligatorios());
			Notification not=new Notification(Notification.ALERT,constants.camposObligatorios());
			not.showPopup();
			return false;
		}
		}else{
		BigDecimal vDevuelto=BigDecimal.valueOf(Double.valueOf(txtDevuelto.getText()));
		if(vDevuelto.compareTo(BigDecimal.ZERO)==1){
			//Dialogs.alert("Alerta", "Existen amortizaciones", null);
			//Window.alert("Existen amortizaciones");
			Notification not=new Notification(Notification.ALERT,"Existen amortizaciones");
			not.showPopup();
			return false;
		}
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
			goToUIPrestamo();
		}else if (event.getSource().equals(this.btnClienteAdd)) {
			goToUIClienteAdd();
			/*dialogCliente = new UIClienteSelect(this);
			setWidthDialog();
			dialogCliente.center();
			dialogCliente.show();*/

		}else if (event.getSource().equals(btnGuardar)) {			
			registrar();
		}
	}

	
}
