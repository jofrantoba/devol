package com.devol.client.view.uihomesesion;

import java.util.Date;

import com.devol.client.model.PanelFlow;
import com.devol.client.resource.MyResource;
import com.devol.client.view.uihomecliente.UIHomeCliente;
import com.devol.client.view.uihomecobrador.UIHomeCobrador;
import com.devol.client.view.uihomecobranza.UIHomeCobranza;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.client.view.uihomereport.UIHomeReport;
import com.devol.client.view.uimenu.UIMenuImpl;
import com.devol.i18n.DevolConstants;
import com.devol.server.model.bean.UsuarioRPC;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;

public class UIHomeSesion extends Composite implements InterUIHomeSesion{
	private DevolConstants constants = GWT.create(DevolConstants.class);
    public static final UIHomeSesion uiHomeSesion=new UIHomeSesion();
	private PanelFlow main;
	//public static AnimationHelper animationHelper;
	private DeckPanel container;
	private UIMenuImpl uiMenuImpl;
	private UIHomeCliente uiHomeCliente;
	private UIHomeCobrador uiHomeCobrador;
	private UIHomeCobranza uiHomeCobranza;
	private UIHomePrestamo uiHomePrestamo;
	private UIHomePrestamo uiHomeHistorial;
	private UIHomeReport uiHomeReport;
	public static UsuarioRPC usuario;
	private Presenter presenter;
	private String uiToken;
	
	private UIHomeSesion() {			
		init();
		style();
	}


	 /*ValueChangeHandler<String> valueChangeHandler=new ValueChangeHandler<String>(){

		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			// TODO Auto-generated method stub
			String historyToken=event.getValue();
			if(historyToken.equalsIgnoreCase("login")){
				if(usuario!=null){
				History.newItem("sesion");
				}
			}else if(historyToken.equalsIgnoreCase("register")){
				if(usuario!=null){
				History.newItem("sesion");
				}
			}else if(historyToken.equalsIgnoreCase("sesion")){
				History.newItem("sesion");
			}
		}

		
		 
	 };*/
	
	private void init() {			
		// TODO Auto-generated method stub
		main = new PanelFlow();
		initWidget(main);
		
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		main.add(container);
		
		uiMenuImpl=new UIMenuImpl(this);
		container.add(uiMenuImpl);				
		
		/*animationHelper = new AnimationHelper();
		animationHelper.getElement().setId("animationHelper");
		container.add(animationHelper);
		uiMenuImpl=new UIMenuImpl();
		
		animationHelper.goTo(uiMenuImpl, Animation.SLIDE_REVERSE);*/
		
		uiHomeCliente=new UIHomeCliente();
		container.add(uiHomeCliente);
		
		uiHomeCobrador=new UIHomeCobrador();
		container.add(uiHomeCobrador);
		
		uiHomeCobranza=new UIHomeCobranza("COBRANZA");
		container.add(uiHomeCobranza);
		
		uiHomePrestamo=new UIHomePrestamo("PRESTAMO");
		container.add(uiHomePrestamo);
		
		uiHomeHistorial=new UIHomePrestamo("HISTORIAL");
		container.add(uiHomeHistorial);
		
		uiHomeReport=new UIHomeReport();
		container.add(uiHomeReport);				
		
		container.showWidget(0);
		uiMenuImpl.scrollPanel.refresh();		
	}
	
	/*private void init() {
		main = new FlowPanel();
		initWidget(main);

		//animationHelper = new AnimationHelper();
		//animationHelper.getElement().setId("animationHelper");
		// animationHelper.setAnimationDuration(1000);
		//main.add(animationHelper);
	}*/

	private void style() {
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		main.setWidth("100%");
		main.addStyleName(MyResource.INSTANCE.getStlUIHome().main());
		//animationHelper.setWidth("100%");
		/*animationHelper.addStyleName(MyResource.INSTANCE.getStlUIHome()
				.animationHelper());*/
	}
	
	public void getUiMenuImpl() {
		container.showWidget(0);			
	}
	
	public UIMenuImpl getUiMenu() {
		return uiMenuImpl;
	}

	public void getUiHomeCliente() {
		container.showWidget(1);
		uiHomeCliente.getContainer().showWidget(0);
		uiHomeCliente.getUIClienteImpl().cargarTabla();		
	}
	
	public void getUiHomeCobrador() {
		container.showWidget(2);
		uiHomeCobrador.reloadTitle();
		uiHomeCobrador.getContainer().showWidget(0);
		uiHomeCobrador.getUICobradorImpl().cargarTabla();		
	}
	
	public void getUiHomeCobranza() {
		container.showWidget(3);		
		uiHomeCobranza.getContainer().showWidget(0);
		uiHomeCobranza.getUiPrestamistaImpl().cargarTabla();		
	}

	public void getUiHomePrestamo() {
		container.showWidget(4);
		uiHomePrestamo.getContainer().showWidget(0);
		uiHomePrestamo.getUIPrestamoImpl().cargarTabla();		
	}

	public void getUiHomeHistorial() {
		container.showWidget(5);
		uiHomeHistorial.getContainer().showWidget(0);
		uiHomeHistorial.getUIPrestamoImpl().cargarTabla();
	}

	public void getUiHomeReport() {
		container.showWidget(6);
		uiHomeReport.getContainer().showWidget(0);
		DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
		Date fechaIni=new Date();
		String fecha=format.format(fechaIni);		
		fechaIni=format.parse(fecha);
		uiHomeReport.getUiRecaudado().cargarTabla(fechaIni);
		
	}
	
	
	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		this.presenter=presenter;
	}

	@Override
	public void setUIToken(String token) {
		// TODO Auto-generated method stub
		this.uiToken=token;
	}

	public Presenter getPresenter() {
		return presenter;
	}	
	
	

}
