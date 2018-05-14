package com.devol.client.view.uimantcobrador;

import java.util.Date;

import com.devol.client.beanproxy.GestorCobranzaProxy;
import com.devol.client.beanproxy.UsuarioProxy;
import com.devol.client.requestfactory.ContextGestionCobranza;
import com.devol.client.requestfactory.ContextGestionUsuario;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecobrador.UIHomeCobrador;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIMantCobradorImpl extends UIMantCobrador {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UIHomeCobrador uiHomeCobrador;
	private UsuarioProxy beanUsuarioCobrador;
	
	public UIMantCobradorImpl(UIHomeCobrador uiHomeCobrador) {
		this.uiHomeCobrador = uiHomeCobrador;
		//super.setHeightContainer(82);
		
	}


	@Override
	public void goToUICobrador() {
		cleanform();
		uiHomeCobrador.getContainer().showWidget(0);	
		uiHomeCobrador.getUICobradorImpl().cargarTabla();			
	}

	@Override
	public void registrar() {
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			if(isValidData()){
			insertar();
			}
		} else if (modo.equalsIgnoreCase(constants.modoDesactivar())) {
			if(isValidData()){
			editar();
			}
		}

	}

	private void insertar() {
		popup.showPopup();
		Date fecha = new Date();
		ContextGestionCobranza context = FACTORY.contextGestionCobranza();
		FACTORY.initialize(EVENTBUS);
		GestorCobranzaProxy bean=context.create(GestorCobranzaProxy.class);
		bean.setIdUsuarioOwner(UIHomeSesion.usuario.getIdUsuario());
		UsuarioProxy beanCobrador=context.create(UsuarioProxy.class); 
		beanCobrador.setDni(this.txtDni.getText());
		beanCobrador.setTelefono(this.txtTelefono.getText());
		beanCobrador.setDireccion(this.txtDireccion.getText());
		beanCobrador.setCorreo(this.txtCorreo.getText());
		beanCobrador.setNombres(this.txtNombre.getText());
		beanCobrador.setApellidos(this.txtApellido.getText());		
		if(beanUsuarioCobrador!=null){
			bean.setIdUsuarioCobrador(beanUsuarioCobrador.getIdUsuario());
			beanCobrador.setIdCreateUsuario(beanUsuarioCobrador.getCorreo());			
		}			
		bean.setBeanUsuarioCobrador(beanCobrador);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		Request<Boolean> request = context.insertarCobrador(bean);
		request.fire(new Receiver<Boolean>() {
			
			@Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                //Window.alert(error.getMessage());
                Notification not=new Notification(Notification.ALERT,error.getMessage());
                not.showPopup();
            }

			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanform();
					//Dialogs.alert(constants.clientes(), constants.registradoCorrectamente(), null);
					//Window.alert(constants.registradoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.registradoCorrectamente());
					not.showPopup();
				}
				popup.hidePopup();
			}
			
		});
	}

	private void editar() {
		popup.showPopup();		
		ContextGestionCobranza context = FACTORY.contextGestionCobranza();
		FACTORY.initialize(EVENTBUS);
		Request<Boolean> request=context.desactivarGestorCobranza(beanCobrador.getIdGestorCobranza());
		request.fire(new Receiver<Boolean>() {
			
			@Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();        
                Notification not=new Notification(Notification.ALERT,error.getMessage());
                not.showPopup();
            }

			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {					
					Notification not=new Notification(Notification.INFORMATION,constants.cobradorDesactivado());
					not.showPopup();
					goToUICobrador();
				}
				popup.hidePopup();
			}
			
		});
	}
	
	@Override
	public void loadFieldsCobrador() {
		// TODO Auto-generated method stub		
		this.txtNombre.setText(beanUsuarioCobrador.getNombres());
		this.txtApellido.setText(beanUsuarioCobrador.getApellidos());
		this.txtDni.setText(beanUsuarioCobrador.getDni());
		this.txtDireccion.setText(beanUsuarioCobrador.getDireccion());
		this.txtTelefono.setText(beanUsuarioCobrador.getTelefono());
	}

	@Override
	public void getCobrador() {
		// TODO Auto-generated method stub
		ContextGestionUsuario context=FACTORY.contextGestionUsuario();
		FACTORY.initialize(EVENTBUS);
		Request<UsuarioProxy> request=context.existeCuenta(this.txtCorreo.getText());
		request.fire(new Receiver<UsuarioProxy>() {

			@Override
			public void onSuccess(UsuarioProxy response) {
				// TODO Auto-generated method stub
				beanUsuarioCobrador=response;
				if(response!=null){					
					loadFieldsCobrador();
				}
			}});
	}

	private void cleanform() {
		txtCorreo.setText("");
		txtDni.setText("");
		txtNombre.setText("");		
		txtApellido.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");		
	}
	
	public void refreshScroll(){
		this.scrollPanel.refresh();
		this.txtCorreo.setFocus(true);
	}
	
	public void setTituloOperacion(String titulo){
		this.btnGuardar.setText(titulo);
	}

}
