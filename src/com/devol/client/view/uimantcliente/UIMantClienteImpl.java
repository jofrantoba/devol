package com.devol.client.view.uimantcliente;

import java.util.Date;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.requestfactory.ContextGestionCliente;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.view.uihomecliente.UIHomeCliente;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIMantClienteImpl extends UIMantCliente {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UIHomeCliente uiHomeCliente;

	public UIMantClienteImpl(UIHomeCliente uiHomeCliente) {
		this.uiHomeCliente = uiHomeCliente;
		//super.setHeightContainer(82);
		
	}

	@Override
	public void goToUICliente() {
		cleanform();
		uiHomeCliente.getUIClienteImpl().cargarTabla();
		uiHomeCliente.getContainer().showWidget(0);		
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
		}

	}

	private void insertar() {
		Date fecha = new Date();
		ContextGestionCliente context = FACTORY.contextGestionCliente();
		FACTORY.initialize(EVENTBUS);
		ClienteProxy bean = context.create(ClienteProxy.class);
		bean.setOperacion("I");
		bean.setVersion(fecha.getTime());
		// bean.setIdCliente("chescot2302@gmail.com");
		//bean.setIdCliente("ag5kZXZvbG1pbmd1aWxsb3IiCxIHVXN1YXJpbyIVY2hlc2NvdDIzMDJAZ21haWwuY29tDA");
		bean.setIdCliente(UIHomeSesion.usuario.getIdUsuario());
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
			}

		});
	}

	private void editar() {
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
			}

		});
	}

	private void eliminar() {
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

}
