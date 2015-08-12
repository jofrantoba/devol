package com.devol.client.view.uimantprestamo;

import java.util.Date;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.requestfactory.ContextGestionPrestamo;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
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

public class UIMantPrestamoImpl extends UIMantPrestamo {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UIHomePrestamo uiHomePrestamo;

	public UIMantPrestamoImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		//super.setHeightContainer(82);
		activarModoPrestamo();
		modoPrestamo=uiHomePrestamo.getModo();		
	}
	

	@Override
	public void goToUIPrestamo() {
		cleanform();
		uiHomePrestamo.getUIPrestamoImpl().cargarTabla();
		uiHomePrestamo.getContainer().showWidget(0);		
	}
	
	@Override
	public void goToUIClienteAdd() {
		// TODO Auto-generated method stub
		//uiHomePrestamo.getHeader().setCenter("CLIENTES");
		uiHomePrestamo.getHeader().getLblTitulo().setText(constants.clientes());
		uiHomePrestamo.getHeader().setVisibleBtnMenu(false);
		uiHomePrestamo.getUiCliente().cargarTabla();		
		uiHomePrestamo.getContainer().showWidget(2);		
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
			if(uiHomePrestamo.getModo().equals("HISTORIAL")){
				eliminar();
			}else{
			if(isValidData()){
			eliminar();
			}
			}
		}			

	}

	private void insertar() {
		Date fecha = new Date();
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		FACTORY.initialize(EVENTBUS);
		PrestamoProxy bean = context.create(PrestamoProxy.class);
		bean.setOperacion("I");
		bean.setVersion(fecha.getTime());
		bean.setIdPrestamo(beanCliente.getIdCliente());
		bean.setFecha(txtFecha.getDate());
		bean.setMonto(Double.parseDouble(txtMonto.getText()));
		bean.setTasa(Double.parseDouble(txtTasa.getText()));
		bean.setaDevolver(Double.parseDouble(txtADevolver.getText()));
		bean.setDevuelto(Double.parseDouble(txtDevuelto.getText()));
		bean.setEstado("P");
		bean.setDni(beanCliente.getDni());
		bean.setNombre(beanCliente.getNombre());
		bean.setApellido(beanCliente.getApellido());
		bean.setBeanCliente(beanCliente);
		bean.setIdUsuario(UIHomeSesion.usuario.getIdUsuario());		
		Request<Boolean> request = context.insertarPrestamo(bean);
		// Request<Boolean> request = context.eliminarCliente(bean);
		request.fire(new Receiver<Boolean>() {
			
			/*@Override
			public void onFailure(ServerFailure error) {
				//Dialogs.alert(constants.alerta(), error.getMessage(), null);
				//Window.alert(error.getMessage());
				Notification not=new Notification(Notification.WARNING,error.getMessage());
				not.showPopup();
			 }*/
			
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanform();
					//Dialogs.alert(constants.prestamos(), constants.registradoCorrectamente(), null);
					//Window.alert(constants.registradoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.registradoCorrectamente());
					not.showPopup();
				}
			}

		});
	}

	private void editar() {
		Date fecha = new Date();
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		FACTORY.initialize(EVENTBUS);
		PrestamoProxy bean = context.create(PrestamoProxy.class);
		bean.setOperacion("A");
		bean.setVersion(fecha.getTime());
		//Window.alert(beanCliente.getIdCliente());
		bean.setCodPrestamo(beanPrestamo.getIdPrestamo());
		bean.setIdUsuario(beanPrestamo.getIdUsuario());
		bean.setFecha(txtFecha.getDate());
		bean.setMonto(Double.parseDouble(txtMonto.getText()));
		bean.setTasa(Double.parseDouble(txtTasa.getText()));
		bean.setaDevolver(Double.parseDouble(txtADevolver.getText()));
		bean.setDevuelto(Double.parseDouble(txtDevuelto.getText()));
		bean.setEstado(beanPrestamo.getEstado());
		bean.setDni(beanCliente.getDni());
		bean.setNombre(beanCliente.getNombre());
		bean.setApellido(beanCliente.getApellido());
		bean.setBeanCliente(beanCliente);
		bean.setIdUsuario(UIHomeSesion.usuario.getIdUsuario());		
		bean.setIdCliente(beanCliente.getIdCliente());
		Request<Boolean> request = context.actualizarPrestamo(bean);
		request.fire(new Receiver<Boolean>() {
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanform();
					//Dialogs.alert(constants.prestamos(), constants.actualizadoCorrectamente(), null);
					//Window.alert(constants.actualizadoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.actualizadoCorrectamente());
					not.showPopup();
					goToUIPrestamo();
				}
			}

		});
	}

	private void eliminar() {
		Date fecha = new Date();
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		FACTORY.initialize(EVENTBUS);
		PrestamoProxy bean = context.create(PrestamoProxy.class);
		bean.setOperacion("E");
		bean.setVersion(fecha.getTime());
		//Window.alert(beanCliente.getIdCliente());
		bean.setCodPrestamo(beanPrestamo.getIdPrestamo());
		bean.setIdUsuario(beanPrestamo.getIdUsuario());
		bean.setFecha(txtFecha.getDate());
		bean.setMonto(Double.parseDouble(txtMonto.getText()));
		bean.setTasa(Double.parseDouble(txtTasa.getText()));
		bean.setaDevolver(Double.parseDouble(txtADevolver.getText()));
		bean.setDevuelto(Double.parseDouble(txtDevuelto.getText()));
		bean.setEstado(beanPrestamo.getEstado());
		bean.setDni(beanCliente.getDni());
		bean.setNombre(beanCliente.getNombre());
		bean.setApellido(beanCliente.getApellido());
		bean.setBeanCliente(beanCliente);
		bean.setIdUsuario(UIHomeSesion.usuario.getIdUsuario());		
		bean.setIdCliente(beanCliente.getIdCliente());		
		Request<Boolean> request = context.eliminarPrestamo(bean);
		request.fire(new Receiver<Boolean>() {
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanform();
					//Dialogs.alert(constants.prestamos(), constants.eliminadoCorrectamente(), null);
					//Window.alert(constants.eliminadoCorrectamente());
					Notification not=new Notification(Notification.INFORMATION,constants.eliminadoCorrectamente());
					not.showPopup();
					goToUIPrestamo();
				}
			}

		});
	}

	private void cleanform() {
		txtCodigo.setText(null);
		txtADevolver.setText(null);
		txtDevuelto.setText(null);
		txtMonto.setText(null);
		txtTasa.setText(null);
		//txtFecha.getTxtFecha().setText(null);
		txtCliente.setText(null);
		beanCliente=null;
	}
	
	public void refreshScroll(){
		this.scrollPanel.refresh();
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
