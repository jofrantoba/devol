package com.devol.client.view.uiprestamo;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.requestfactory.ContextGestionPrestamo;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecobranza.UIHomeCobranza;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIPrestamoImpl extends UIPrestamo {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<PrestamoProxy> lista;
	private UIHomePrestamo uiHomePrestamo;
	private UIHomeCobranza uiHomeCobranza;	
	private boolean flagHistorial=false;

	public UIPrestamoImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		// super.setHeightContainer(128);
		//try{
		//cargarTabla();
		activarModoPrestamo();
		//}catch(Exception ex){}
		//this.scrollPanel.refresh();	
	}
	
	public UIPrestamoImpl(UIHomeCobranza uiHomeCobranza) {
		this.uiHomeCobranza = uiHomeCobranza;
		activarModoPrestamo();	
	}
	

	@Override
	public void goToUIMantPrestamo(String modo) {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			uiHomePrestamo.getUIMantPrestamoImpl().setModo(modo);
			uiHomePrestamo.getUIMantPrestamoImpl().activarCampos();
			uiHomePrestamo.getUIMantPrestamoImpl().setTituloOperacion("Insertar");
			uiHomePrestamo.getContainer().showWidget(1);			
			uiHomePrestamo.getUIMantPrestamoImpl().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoEditar())) {
			PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccionPrestamo(), null);
				//Window.alert(constants.seleccionPrestamo());
				Notification not=new Notification(Notification.ALERT,constants.seleccionPrestamo());
				not.showPopup();
				return;	
			}				
			uiHomePrestamo.getUIMantPrestamoImpl().setModo(modo);
			uiHomePrestamo.getUIMantPrestamoImpl().setBean(bean);
			uiHomePrestamo.getUIMantPrestamoImpl().activarCampos();
			uiHomePrestamo.getUIMantPrestamoImpl().setTituloOperacion("Actualizar");
			uiHomePrestamo.getContainer().showWidget(1);			
			uiHomePrestamo.getUIMantPrestamoImpl().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
			PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccionPrestamo(), null);
				//Window.alert(constants.seleccionPrestamo());
				Notification not=new Notification(Notification.ALERT,constants.seleccionPrestamo());
				not.showPopup();
				return;	
			}				
			uiHomePrestamo.getUIMantPrestamoImpl().setModo(modo);
			uiHomePrestamo.getUIMantPrestamoImpl().setBean(bean);
			uiHomePrestamo.getUIMantPrestamoImpl().activarCampos();
			uiHomePrestamo.getUIMantPrestamoImpl().setTituloOperacion("Eliminar");
			uiHomePrestamo.getContainer().showWidget(1);			
			uiHomePrestamo.getUIMantPrestamoImpl().refreshScroll();			
		}

	}

	@Override
	public void cargarTabla() {
		popup.showPopup();
		if(UIHomeSesion.usuario!=null){
		lista = new ArrayList<PrestamoProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		String estadoPrestamo="";
		if(uiHomePrestamo.getModo().equals("HISTORIAL")){
			estadoPrestamo="A";			
		}else{
			estadoPrestamo="P";
		}
		Request<List<PrestamoProxy>> request = context.listarPrestamoByUsuario(UIHomeSesion.usuario.getIdUsuario(), estadoPrestamo).with("beanCliente");
		//Request<List<PrestamoProxy>> request = context.listarPrestamo();
		request.fire(new Receiver<List<PrestamoProxy>>() {
			@Override
			public void onSuccess(List<PrestamoProxy> response) {
				// TODO Auto-generated method stub
				/*Iterator<PrestamoProxy> i=response.iterator(); 
				  while(i.hasNext()){
					  PrestamoProxy c=i.next(); 
				  com.google.gwt.user.client.Window.alert(c.getBeanCliente().getNombre()+c.getBeanCliente().getApellido()); }*/
				lista.addAll(response);							
				grid.setData(lista);		
				grid.getSelectionModel().clear();
				popup.hidePopup();
			}
			
			@Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                //Window.alert(error.getMessage());
                Notification not=new Notification(Notification.WARNING,error.getMessage());
                not.showPopup();
            }
			
		});
		}
	}
	
	@Override
	public void goToUIAmortizacion() {
		
		// TODO Auto-generated method stub
		PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();		
		//Window.alert(bean.getIdCliente());
		if (bean == null){
			//Dialogs.alert(constants.alerta(),constants.seleccionPrestamo(), null);
			//Window.alert(constants.seleccionPrestamo());
			Notification not=new Notification(Notification.ALERT,constants.seleccionPrestamo());
			not.showPopup();
			return;	
		}		
		if(!uiHomePrestamo.getModo().equals("HISTORIAL")){
			sendPrestamoHistorial(bean.getIdPrestamo());			
		}			
		uiHomePrestamo.getUiAmortizacionImpl().setBean(bean);
		uiHomePrestamo.getUiAmortizacionImpl().cargarTabla();
		uiHomePrestamo.getContainer().showWidget(3);					
	}	
	
	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		if(uiHomePrestamo.getModo().equals("HISTORIAL")){
			uiHomePrestamo.getHeader().getLblTitulo().setText(constants.historial());
			toolBar.getBtnNuevo().setVisible(false);			
		}else{
			toolBar.getBtnNuevo().setVisible(true);
		}
	}
	
	@Override
	public boolean sendPrestamoHistorial(String idPrestamo) {
		// TODO Auto-generated method stub		
		flagHistorial=false;
		FACTORY.initialize(EVENTBUS);
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		Request<Boolean> request =context.sendPrestamoHistorial(idPrestamo);		
		request.fire(new Receiver<Boolean>() {					

			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if(response){
					Notification not=new Notification(Notification.INFORMATION,constants.prestamoEnviadoHistoria());
					not.showPopup();
					flagHistorial=response;					
				}
			}});
		return flagHistorial;
	}
}
