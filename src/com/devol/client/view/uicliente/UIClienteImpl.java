package com.devol.client.view.uicliente;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.requestfactory.ContextGestionCliente;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.view.uihomecliente.UIHomeCliente;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;

public class UIClienteImpl extends UICliente {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<ClienteProxy> lista;
	private UIHomeCliente uiHomeCliente;
	private UIHomePrestamo uiHomePrestamo;

	public UIClienteImpl(UIHomeCliente uiHomeCliente) {
		this.uiHomeCliente = uiHomeCliente;
		header.setVisible(false);
		toolBar.setVisible(true);		
		// super.setHeightContainer(128);
		//try{
		//cargarTabla();
		//}catch(Exception ex){}
		//this.scrollPanel.refresh();		
	}
	
	public UIClienteImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		header.setVisible(true);
		toolBar.setVisible(false);
		// super.setHeightContainer(128);
		//try{
		//cargarTabla();
		//}catch(Exception ex){}
		//this.scrollPanel.refresh();		
	}
	
	

	@Override
	public void goToUICMantCliente(String modo) {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			uiHomeCliente.getUIMantCliente().setModo(modo);
			uiHomeCliente.getUIMantCliente().activarCampos();
			uiHomeCliente.getContainer().showWidget(1);			
			uiHomeCliente.getUIMantCliente().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoEditar())) {
			ClienteProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccioneCliente(), null);
				//Window.alert(constants.seleccioneCliente());
				Notification not=new Notification(Notification.ALERT,constants.seleccioneCliente());
				not.showPopup();
				return;
			}				
			uiHomeCliente.getUIMantCliente().setModo(modo);
			uiHomeCliente.getUIMantCliente().setBean(bean);
			uiHomeCliente.getUIMantCliente().activarCampos();
			uiHomeCliente.getContainer().showWidget(1);			
			uiHomeCliente.getUIMantCliente().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
			ClienteProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(),constants.seleccioneCliente(), null);
				//Window.alert(constants.seleccioneCliente());
				Notification not=new Notification(Notification.ALERT,constants.seleccioneCliente());
				not.showPopup();
				return;
			}				
			uiHomeCliente.getUIMantCliente().setModo(modo);
			uiHomeCliente.getUIMantCliente().setBean(bean);
			uiHomeCliente.getUIMantCliente().activarCampos();
			uiHomeCliente.getContainer().showWidget(1);			
			uiHomeCliente.getUIMantCliente().refreshScroll();
		}

	}

	@Override
	public void cargarTabla() {
		if(UIHomeSesion.usuario!=null){
		lista = new ArrayList<ClienteProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionCliente context = FACTORY.contextGestionCliente();
		Request<List<ClienteProxy>> request = context
				.listarClienteByUsuario(UIHomeSesion.usuario.getIdUsuario()).with("beanUsuario");
		request.fire(new Receiver<List<ClienteProxy>>() {
			@Override
			public void onSuccess(List<ClienteProxy> response) {
				// TODO Auto-generated method stub
				/*Iterator<ClienteProxy> i=response.iterator(); 
				  while(i.hasNext()){
					  ClienteProxy c=i.next(); 
				  com.google.gwt.user.client.Window.alert("Tamaño"+c.getListPrestamo().size()); }*/
				lista = response;	
				//Window.alert(lista.get(0).getBeanUsuario().getCorreo());
				grid.getSelectionModel().clear();
				grid.setData(lista);				
				double alto=lista.size()*45;
				container.setHeight(alto+"px");				
				scrollPanel.refresh();
			}
		});
		}
	}	
	
	@Override
	public void goToBack() {
		// TODO Auto-generated method stub
		uiHomePrestamo.getHeader().getLblTitulo().setText("DEVOL");
		uiHomePrestamo.getHeader().setVisibleBtnMenu(true);
		uiHomePrestamo.getContainer().showWidget(1);		
	}

	@Override
	public void selecciona() {
		// TODO Auto-generated method stub		
		ClienteProxy bean = grid.getSelectionModel().getSelectedObject();
		if(bean!=null){				
		uiHomePrestamo.getHeader().getLblTitulo().setText("DEVOL");
		uiHomePrestamo.getHeader().setVisibleBtnMenu(true);		
		uiHomePrestamo.getUIMantPrestamoImpl().setBeanCliente(bean);
		uiHomePrestamo.getContainer().showWidget(1);		
		}else{
			//Dialogs.alert(constants.alerta(), constants.seleccioneCliente(), null);
			//Window.alert(constants.seleccioneCliente());
			Notification not=new Notification(Notification.ALERT,constants.seleccioneCliente());
			not.showPopup();
		}
	}
	
	
}
