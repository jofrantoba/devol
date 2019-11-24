package com.devol.client.view.uiprestamista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devol.client.beanproxy.GestorCobranzaProxy;
import com.devol.client.requestfactory.ContextGestionCobranza;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecobranza.UIHomeCobranza;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.devol.shared.TableToExcel;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIPrestamistaImpl extends UIPrestamista {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<GestorCobranzaProxy> lista;
	private UIHomeCobranza uiHomeCobranza;
	
	public UIPrestamistaImpl(UIHomeCobranza uiHomeCobranza) {
		this.uiHomeCobranza = uiHomeCobranza;		
		toolBar.setVisible(true);				
	}
	
	@Override
	public void cargarTabla() {
		popup.showPopup();
		if(UIHomeSesion.usuario!=null){
		lista = new ArrayList<GestorCobranzaProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionCobranza context = FACTORY.contextGestionCobranza();
		Request<List<GestorCobranzaProxy>> request = context.listarPrestamistaByCobrador(UIHomeSesion.usuario.getIdUsuario()).with("beanUsuarioOwner");
		request.fire(new Receiver<List<GestorCobranzaProxy>>() {
			@Override
			public void onSuccess(List<GestorCobranzaProxy> response) {				
				lista.addAll(response);
				grid.setData(lista);		
				grid.getSelectionModel().clear();;
				popup.hidePopup();
			}
			
			@Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();                
                Notification not=new Notification(Notification.WARNING,error.getMessage());
                not.showPopup();
            }
			
		});
		}
	}
	
	@Override
	public void goToUiPrestamo() {
		// TODO Auto-generated method stub
		GestorCobranzaProxy bean = grid.getSelectionModel().getSelectedObject();
		//Window.alert(bean.getIdCliente());
		if (bean == null){
			//Dialogs.alert(constants.alerta(), constants.seleccioneCliente(), null);
			//Window.alert(constants.seleccioneCliente());
			Notification not=new Notification(Notification.ALERT,constants.seleccionePrestamista());
			not.showPopup();
			return;
		}
		uiHomeCobranza.getContainer().showWidget(1);
		uiHomeCobranza.getUIPrestamoImpl().setBeanGestorCobranza(bean);
		uiHomeCobranza.getUIPrestamoImpl().cargarPrestamoGestorCobranza();
		uiHomeCobranza.setTitle("Prestamos");
	}
	
	@Override
	public void exportarData() {
		// TODO Auto-generated method stub
		Date date=new Date();
		String nameFile="prestamistas-"+date.getTime()+".xls";
		TableToExcel.save(grid,nameFile);
	}
	
	
}
