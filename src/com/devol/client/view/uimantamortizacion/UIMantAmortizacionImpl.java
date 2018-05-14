package com.devol.client.view.uimantamortizacion;

import java.math.BigDecimal;
import java.util.Date;

import com.devol.client.beanproxy.AmortizacionProxy;
import com.devol.client.requestfactory.ContextGestionPrestamo;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecobranza.UIHomeCobranza;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIMantAmortizacionImpl extends UIMantAmortizacion {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UIHomePrestamo uiHomePrestamo;
	private UIHomeCobranza uiHomeCobranza;	

	public UIMantAmortizacionImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		//super.setHeightContainer(82);
		activarModoPrestamo();		
	}
	
	public UIMantAmortizacionImpl(UIHomeCobranza uiHomeCobranza) {
		this.uiHomeCobranza = uiHomeCobranza;
		//super.setHeightContainer(82);
		activarModoPrestamo();		
	}
	
	
	@Override
	public void goToUIAmortizacion() {
		cleanform();				
		uiHomePrestamo.getContainer().showWidget(3);
		uiHomePrestamo.getUiAmortizacionImpl().cargarTabla();			
	}
	
	@Override
	public void registrar() {
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			if(isValidData()){
				insertar();
			}			
		}else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
			//if(isValidData()){
			eliminar();
			//}
		}

	}
	
	@Override
	public void actualizarSaldos() {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {						
			BigDecimal vAdevolver=BigDecimal.valueOf(Double.valueOf(uiHomePrestamo.getUiAmortizacionImpl().getLblADevolver().getText()));
			BigDecimal vDevuelto=BigDecimal.valueOf(Double.valueOf(uiHomePrestamo.getUiAmortizacionImpl().getLblADevuelto().getText()));						
			BigDecimal vAmortizado=BigDecimal.valueOf(Double.parseDouble(txtMonto.getText()));
			vDevuelto=vDevuelto.add(vAmortizado);
			vAdevolver=vAdevolver.subtract(vAmortizado);
			this.vADevolver=vAdevolver;
			uiHomePrestamo.getUiAmortizacionImpl().getLblADevuelto().setText(vDevuelto.toString());
			uiHomePrestamo.getUiAmortizacionImpl().getLblADevolver().setText(vAdevolver.toString());
			//Window.alert(this.vADevolver.toString());
		}else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
			BigDecimal vAdevolver=BigDecimal.valueOf(Double.valueOf(uiHomePrestamo.getUiAmortizacionImpl().getLblADevolver().getText()));
			BigDecimal vDevuelto=BigDecimal.valueOf(Double.valueOf(uiHomePrestamo.getUiAmortizacionImpl().getLblADevuelto().getText()));						
			BigDecimal vAmortizado=BigDecimal.valueOf(Double.parseDouble(txtMonto.getText()));
			vDevuelto=vDevuelto.subtract(vAmortizado);
			vAdevolver=vAdevolver.add(vAmortizado);	
			this.vADevolver=vAdevolver;
			uiHomePrestamo.getUiAmortizacionImpl().getLblADevuelto().setText(vDevuelto.toString());
			uiHomePrestamo.getUiAmortizacionImpl().getLblADevolver().setText(vAdevolver.toString());			
			//Window.alert(this.vADevolver.toString());
		}
	}
	
	
	private void insertar() {
		popup.showPopup();
		Date fecha = new Date();
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		FACTORY.initialize(EVENTBUS);
		AmortizacionProxy bean = context.create(AmortizacionProxy.class);
		bean.setOperacion("I");
		bean.setVersion(fecha.getTime());
		bean.setIdCreateAmortizacion(beanPrestamo.getIdPrestamo());
		bean.setFecha(txtFecha.getDate());
		bean.setMonto(Double.parseDouble(txtMonto.getText()));						
		bean.setIdUsuario(UIHomeSesion.usuario.getIdUsuario());			
		Request<Boolean> request = context.insertarAmortizacion(bean);
		// Request<Boolean> request = context.eliminarCliente(bean);
		request.fire(new Receiver<Boolean>() {
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					actualizarSaldos();
					cleanform();
					//Dialogs.alert(constants.amortizar(), constants.registradoCorrectamente(), null);					
					//Window.alert(constants.registradoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.registradoCorrectamente());
					not.showPopup();
				}
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
	
	private void eliminar() {
		popup.showPopup();
		Date fecha = new Date();
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		FACTORY.initialize(EVENTBUS);
		AmortizacionProxy bean = context.create(AmortizacionProxy.class);
		bean.setOperacion("E");
		bean.setVersion(fecha.getTime());
		bean.setCodAmortizacion(beanAmortizacion.getIdAmortizacion());
		bean.setFecha(txtFecha.getDate());
		bean.setMonto(Double.parseDouble(txtMonto.getText()));						
		bean.setIdUsuario(UIHomeSesion.usuario.getIdUsuario());	
		bean.setIdPrestamo(beanAmortizacion.getIdPrestamo());
		Request<Boolean> request = context.eliminarAmortizacion(bean);
		// Request<Boolean> request = context.eliminarCliente(bean);
		request.fire(new Receiver<Boolean>() {
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					actualizarSaldos();
					cleanform();
					//Dialogs.alert(constants.amortizar(), constants.eliminadoCorrectamente(), null);
					//Window.alert(constants.eliminadoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.eliminadoCorrectamente());
					not.showPopup();
					goToUIAmortizacion();
				}
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
	
	public void refreshScroll(){
		this.scrollPanel.refresh();
	}
	
	private void cleanform() {
		txtCodigo.setText(null);		
		txtMonto.setText(null);		
		//txtFecha.getTxtFecha().setText(null);				
	}
	
	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		if(uiHomePrestamo.getModo().equals("HISTORIAL")){			
				btnGuardar.setVisible(false);									
		}else{
			btnGuardar.setVisible(true);
		}
	}
}
