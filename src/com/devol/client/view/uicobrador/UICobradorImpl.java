package com.devol.client.view.uicobrador;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.GestorCobranzaProxy;
import com.devol.client.requestfactory.ContextGestionCobranza;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecobrador.UIHomeCobrador;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UICobradorImpl extends UICobrador {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<GestorCobranzaProxy> lista;
	private UIHomeCobrador uiHomeCobrador;
	
	public UICobradorImpl(UIHomeCobrador uiHomeCobrador) {
		this.uiHomeCobrador = uiHomeCobrador;
		header.setVisible(false);
		toolBar.setVisible(true);				
	}
	
	@Override
	public void cargarTabla() {
		popup.showPopup();
		if(UIHomeSesion.usuario!=null){
		lista = new ArrayList<GestorCobranzaProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionCobranza context = FACTORY.contextGestionCobranza();
		Request<List<GestorCobranzaProxy>> request = context
				.listarGestorCobranzaByUsuario(UIHomeSesion.usuario.getIdUsuario()).with("beanUsuarioCobrador");
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
	public void goToUICMantCobrador(String modo) {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			uiHomeCobrador.getUIMantCobrador().setModo(modo);
			uiHomeCobrador.getUIMantCobrador().activarCampos();
			uiHomeCobrador.getUIMantCobrador().setTituloOperacion("Insertar");
			uiHomeCobrador.getContainer().showWidget(1);			
			uiHomeCobrador.getUIMantCobrador().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoDesactivar())) {
			GestorCobranzaProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccioneCliente(), null);
				//Window.alert(constants.seleccioneCliente());
				Notification not=new Notification(Notification.ALERT,constants.seleccioneCobrador());
				not.showPopup();
				return;
			}				
			uiHomeCobrador.getUIMantCobrador().setModo(modo);
			uiHomeCobrador.getUIMantCobrador().setBean(bean);
			uiHomeCobrador.getUIMantCobrador().activarCampos();
			uiHomeCobrador.getUIMantCobrador().setTituloOperacion("Desactivar");
			uiHomeCobrador.getContainer().showWidget(1);			
			uiHomeCobrador.getUIMantCobrador().refreshScroll();
		}

	}
	
	@Override
	public void goToUiCliente() {
		GestorCobranzaProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean == null){
			//Dialogs.alert(constants.alerta(),constants.seleccionPrestamo(), null);
			//Window.alert(constants.seleccionPrestamo());
			Notification not=new Notification(Notification.ALERT,constants.seleccioneCobrador());
			not.showPopup();
			return;	
		}
		uiHomeCobrador.getUiClienteImpl().setBeanGestorCobranza(bean);
		uiHomeCobrador.getUiClienteImpl().cargarClientesAsignados();
		uiHomeCobrador.getContainer().showWidget(2);
	}
	
}
