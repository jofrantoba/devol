package com.devol.client.view.uimenu;

import com.devol.client.service.ServiceGestionUsuario;
import com.devol.client.service.ServiceGestionUsuarioAsync;
import com.devol.client.util.Notification;
import com.devol.client.view.uihome.UIHome;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class UIMenuImpl extends UIMenu {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final ServiceGestionUsuarioAsync SERVICE = GWT
			.create(ServiceGestionUsuario.class);
	private UIHomeSesion uiHomeSesion;

	public UIMenuImpl(UIHomeSesion uiHomeSesion) {
		this.uiHomeSesion=uiHomeSesion;					
	}

	@Override
	public void viewMenuItem(String item) {		
		// TODO Auto-generated method stub		
		if (item.equalsIgnoreCase(constants.clientes())) {
			//Window.alert(item);
			/*UIHomeCliente uiHomeCliente=new UIHomeCliente();*/
			//uiHomeCliente.getUIClienteImpl().refreshScroll();
			//uiHomeCliente.getUIClienteImpl().cargarTabla();
			//uiHomeCliente.getUIClienteImpl().refreshScroll();
			/*UIHomeSesion.animationHelper.goTo(uiHomeCliente, Animations.SLIDE);*/			
			//uiHomeCliente.getUIClienteImpl().cargarTabla();		
			UIHomeSesion.uiHomeSesion.getUiHomeCliente();			
		}else if (item.equalsIgnoreCase(constants.cobrador())) {
			/*UIHomePrestamo uiHomePrestamo=new UIHomePrestamo("PRESTAMO");*/
			/*UIHomeSesion.animationHelper.goTo(uiHomePrestamo, Animations.SLIDE);*/
			UIHomeSesion.uiHomeSesion.getUiHomeCobrador();			
		}else if (item.equalsIgnoreCase(constants.prestamos())) {
			/*UIHomePrestamo uiHomePrestamo=new UIHomePrestamo("PRESTAMO");*/
			/*UIHomeSesion.animationHelper.goTo(uiHomePrestamo, Animations.SLIDE);*/
			UIHomeSesion.uiHomeSesion.getUiHomePrestamo();
		}else if (item.equalsIgnoreCase(constants.cobranza())) {
			/*UIHomePrestamo uiHomePrestamo=new UIHomePrestamo("PRESTAMO");*/
			/*UIHomeSesion.animationHelper.goTo(uiHomePrestamo, Animations.SLIDE);*/
			UIHomeSesion.uiHomeSesion.getUiHomeCobranza();
		}else if (item.equalsIgnoreCase(constants.historial())) {
			/*UIHomePrestamo uiHomePrestamo=new UIHomePrestamo("HISTORIAL");*/
			/*UIHomeSesion.animationHelper.goTo(uiHomePrestamo, Animations.SLIDE);*/
			UIHomeSesion.uiHomeSesion.getUiHomeHistorial();
		}else if (item.equalsIgnoreCase(constants.reportes())) {
			/*UIHomeReport uiHomeReport=new UIHomeReport();*/
			/*UIHomeSesion.animationHelper.goTo(uiHomeReport, Animations.SLIDE);*/
			UIHomeSesion.uiHomeSesion.getUiHomeReport();
		}else if (item.equalsIgnoreCase(constants.salir())) {
			SERVICE.logout(new AsyncCallback<Boolean>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					//Window.alert(caught.getMessage());
					Notification not=new Notification(caught.getMessage(),Notification.WARNING);
					not.showPopup();
				}

				@Override
				public void onSuccess(Boolean result) {
					// TODO Auto-generated method stub
					if(result){
					UIHomeSesion.usuario=null;				
					UIHome uiHome=new UIHome();
					RootPanel.get().clear();
					RootPanel.get().add(uiHome);
					}
				}});						
		}	
		selectionModel.clear();
	}
	
	public void refreshScroll(){
		this.scrollPanel.refresh();
	}
}
