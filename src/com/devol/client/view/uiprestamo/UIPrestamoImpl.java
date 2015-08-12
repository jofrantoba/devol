package com.devol.client.view.uiprestamo;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.requestfactory.ContextGestionPrestamo;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
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

public class UIPrestamoImpl extends UIPrestamo {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<PrestamoProxy> lista;
	private UIHomePrestamo uiHomePrestamo;

	public UIPrestamoImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		// super.setHeightContainer(128);
		//try{
		//cargarTabla();
		activarModoPrestamo();
		//}catch(Exception ex){}
		//this.scrollPanel.refresh();	
	}
	

	@Override
	public void goToUIMantPrestamo(String modo) {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			uiHomePrestamo.getUIMantPrestamoImpl().setModo(modo);
			uiHomePrestamo.getUIMantPrestamoImpl().activarCampos();
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
			uiHomePrestamo.getContainer().showWidget(1);			
			uiHomePrestamo.getUIMantPrestamoImpl().refreshScroll();			
		}

	}

	@Override
	public void cargarTabla() {
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
				lista = response;
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
		uiHomePrestamo.getUiAmortizacionImpl().setBean(bean);
		uiHomePrestamo.getUiAmortizacionImpl().cargarTabla();
		uiHomePrestamo.getContainer().showWidget(3);		
	}
	
	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		if(uiHomePrestamo.getModo().equals("HISTORIAL")){
			toolBar.getBtnNuevo().setVisible(false);			
		}else{
			toolBar.getBtnNuevo().setVisible(true);
		}
	}
}
