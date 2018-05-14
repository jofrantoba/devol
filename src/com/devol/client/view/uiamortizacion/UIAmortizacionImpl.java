package com.devol.client.view.uiamortizacion;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.AmortizacionProxy;
import com.devol.client.requestfactory.ContextGestionPrestamo;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecobranza.UIHomeCobranza;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIAmortizacionImpl extends UIAmortizacion {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<AmortizacionProxy> lista;
	private UIHomePrestamo uiHomePrestamo;
	private UIHomeCobranza uiHomeCobranza;	

	public UIAmortizacionImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		setHeightContainer(170);	
		activarModoPrestamo();	
	}
	
	public UIAmortizacionImpl(UIHomeCobranza uiHomeCobranza) {
		this.uiHomeCobranza = uiHomeCobranza;
		setHeightContainer(170);	
		activarModoPrestamo();	
	}
	
	@Override
	public void goToUIMantAmortizacion(String modo) {		
		// TODO Auto-generated method stub
				if (modo.equalsIgnoreCase(constants.modoNuevo())) {
					uiHomePrestamo.getUiMantAmortizacionImpl().setModo(modo);
					uiHomePrestamo.getUiMantAmortizacionImpl().setBean(null,beanPrestamo,lblADevolver.getText());
					uiHomePrestamo.getUiMantAmortizacionImpl().activarCampos();
					uiHomePrestamo.getContainer().showWidget(4);					
					uiHomePrestamo.getUiMantAmortizacionImpl().refreshScroll();
				} else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
					AmortizacionProxy bean = grid.getSelectionModel().getSelectedObject();
					//Window.alert(bean.getIdCliente());
					if (bean == null){
						//Dialogs.alert(constants.alerta(), constants.seleccionAmortizacion(), null);
						//Window.alert(constants.seleccionAmortizacion());
						Notification not=new Notification(Notification.ALERT,constants.seleccionAmortizacion());
						not.showPopup();
						return;	
					}				
					uiHomePrestamo.getUiMantAmortizacionImpl().setModo(modo);
					uiHomePrestamo.getUiMantAmortizacionImpl().setBean(bean,beanPrestamo,lblADevolver.getText());
					uiHomePrestamo.getUiMantAmortizacionImpl().activarCampos();
					uiHomePrestamo.getContainer().showWidget(4);					
					uiHomePrestamo.getUiMantAmortizacionImpl().refreshScroll();
				}
	}

	@Override
	public void goToUIPrestamo() {
		// TODO Auto-generated method stub
		if(!uiHomePrestamo.getModo().equals("HISTORIAL")){
			uiHomePrestamo.getUIPrestamoImpl().sendPrestamoHistorial(beanPrestamo.getIdPrestamo());
		}		
		uiHomePrestamo.getContainer().showWidget(0);
		uiHomePrestamo.getUIPrestamoImpl().cargarTabla();			
	}
	
	@Override
	public void cargarTabla() {
		popup.showPopup();
		lista = new ArrayList<AmortizacionProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		Request<List<AmortizacionProxy>> request = context.listarAmortizacionByPrestamo(beanPrestamo.getIdPrestamo());
		//Request<List<PrestamoProxy>> request = context.listarPrestamo();
		request.fire(new Receiver<List<AmortizacionProxy>>() {
			@Override
			public void onSuccess(List<AmortizacionProxy> response) {
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
	
	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		if(uiHomePrestamo.getModo().equals("HISTORIAL")){
			btnNuevo.setVisible(false);	
			btnEliminar.setVisible(false);
		}else{
			btnNuevo.setVisible(true);
			btnEliminar.setVisible(true);
		}
	}
	

}
