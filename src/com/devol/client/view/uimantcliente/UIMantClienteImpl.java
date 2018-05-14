package com.devol.client.view.uimantcliente;

import java.util.Date;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.requestfactory.ContextGestionCliente;
import com.devol.client.requestfactory.ContextGestionCobranza;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecliente.UIHomeCliente;
import com.devol.client.view.uihomecobrador.UIHomeCobrador;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIMantClienteImpl extends UIMantCliente {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UIHomeCliente uiHomeCliente;
	private UIHomeCobrador uiHomeCobrador;

	public UIMantClienteImpl(UIHomeCliente uiHomeCliente) {
		this.uiHomeCliente = uiHomeCliente;
		//super.setHeightContainer(82);
		
	}
	
	public UIMantClienteImpl(UIHomeCobrador uiHomeCobrador) {
		this.uiHomeCobrador = uiHomeCobrador;
		//super.setHeightContainer(82);
		
	}

	@Override
	public void goToUICliente() {
		cleanform();
		if(uiHomeCliente!=null){
			uiHomeCliente.getContainer().showWidget(0);	
			uiHomeCliente.getUIClienteImpl().cargarTabla();
		}else if(uiHomeCobrador!=null){
			uiHomeCobrador.getContainer().showWidget(2);	
			uiHomeCobrador.getUiClienteImpl().cargarClientesAsignados();
		}
					
	}

	@Override
	public void registrar() {
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			if(isValidData()){
			insertar();
			}
		} else if (modo.equalsIgnoreCase(constants.modoEditar())) {
			if(isValidData()){
			editar();
			}
		} else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
			if(isValidData()){
				eliminar();
			}			
		} else if (modo.equalsIgnoreCase(constants.modoDesactivar())) {			
				desactivar();			
		}

	}

	private void insertar() {
		popup.showPopup();
		Date fecha = new Date();
		ContextGestionCliente context = FACTORY.contextGestionCliente();
		FACTORY.initialize(EVENTBUS);
		ClienteProxy bean = context.create(ClienteProxy.class);
		bean.setOperacion("I");
		bean.setVersion(fecha.getTime());
		// bean.setIdCliente("chescot2302@gmail.com");
		//bean.setIdCliente("ag5kZXZvbG1pbmd1aWxsb3IiCxIHVXN1YXJpbyIVY2hlc2NvdDIzMDJAZ21haWwuY29tDA");
		bean.setIdCreateCliente(UIHomeSesion.usuario.getIdUsuario());
		bean.setDni(txtDni.getText());
		bean.setNombre(txtNombre.getText());
		bean.setApellido(txtApellido.getText());
		bean.setTelefono(txtTelefono.getText());
		bean.setDireccion(txtDireccion.getText());
		Request<Boolean> request = context.insertarCliente(bean);
		// Request<Boolean> request = context.eliminarCliente(bean);
		request.fire(new Receiver<Boolean>() {
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
			
			@Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                //Window.alert(error.getMessage());
                Notification not=new Notification(Notification.WARNING,error.getMessage());
                not.showPopup();
            }

		});
	}

	private void editar() {
		popup.showPopup();
		Date fecha = new Date();
		ContextGestionCliente context = FACTORY.contextGestionCliente();
		FACTORY.initialize(EVENTBUS);
		ClienteProxy bean = context.create(ClienteProxy.class);
		bean.setOperacion("A");
		bean.setVersion(fecha.getTime());
		//Window.alert(beanCliente.getIdCliente());
		bean.setCodCliente(beanCliente.getIdCliente());
		bean.setIdUsuario(beanCliente.getIdUsuario());
		bean.setDni(txtDni.getText());
		bean.setNombre(txtNombre.getText());
		bean.setApellido(txtApellido.getText());
		bean.setTelefono(txtTelefono.getText());
		bean.setDireccion(txtDireccion.getText());	
		bean.setNumPrestamo(beanCliente.getNumPrestamo());
		Request<Boolean> request = context.actualizarCliente(bean);
		request.fire(new Receiver<Boolean>() {
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanform();
					//Dialogs.alert(constants.clientes(), constants.actualizadoCorrectamente(), null);
					//Window.alert(constants.actualizadoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.actualizadoCorrectamente());
					not.showPopup();
					goToUICliente();
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
	
	private void desactivar() {
		popup.showPopup();		
		ContextGestionCobranza context = FACTORY.contextGestionCobranza();
		FACTORY.initialize(EVENTBUS);
		Request<Boolean> request = context.desactivarGestorCliente(beanCliente.getIdGestorCliente());
		request.fire(new Receiver<Boolean>() {
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanform();
					//Dialogs.alert(constants.clientes(), constants.actualizadoCorrectamente(), null);
					//Window.alert(constants.actualizadoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.actualizadoCorrectamente());
					not.showPopup();
					goToUICliente();
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
		ContextGestionCliente context = FACTORY.contextGestionCliente();
		FACTORY.initialize(EVENTBUS);
		ClienteProxy bean = context.create(ClienteProxy.class);
		bean.setOperacion("E");
		bean.setVersion(fecha.getTime());
		//Window.alert(beanCliente.getIdCliente());
		bean.setCodCliente(beanCliente.getIdCliente());
		bean.setIdUsuario(beanCliente.getIdUsuario());
		bean.setNumPrestamo(beanCliente.getNumPrestamo());
		bean.setDni(txtDni.getText());
		bean.setNombre(txtNombre.getText());
		bean.setApellido(txtApellido.getText());
		bean.setTelefono(txtTelefono.getText());
		bean.setDireccion(txtDireccion.getText());		
		Request<Boolean> request = context.eliminarCliente(bean);
		request.fire(new Receiver<Boolean>() {
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanform();
					//Dialogs.alert(constants.clientes(), constants.eliminadoCorrectamente(), null);
					//Window.alert(constants.eliminadoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.eliminadoCorrectamente());
					not.showPopup();
					goToUICliente();
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
			
			/*public void onFailure(ServerFailure error) {
				//Dialogs.alert(constants.alerta(), error.getMessage(), null);
				//Window.alert(error.getMessage());
				Notification not=new Notification(Notification.WARNING,error.getMessage());
				not.showPopup();
			  }*/

		});
	}

	private void cleanform() {
		txtDni.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
	}
	
	public void refreshScroll(){
		this.scrollPanel.refresh();
	}
	
	public void setTituloOperacion(String titulo){
		this.btnGuardar.setText(titulo);
	}

}
