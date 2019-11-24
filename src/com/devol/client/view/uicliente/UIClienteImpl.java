package com.devol.client.view.uicliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.beanproxy.GestorCobranzaProxy;
import com.devol.client.requestfactory.ContextGestionCliente;
import com.devol.client.requestfactory.ContextGestionCobranza;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.resource.MyResource;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecliente.UIHomeCliente;
import com.devol.client.view.uihomecobrador.UIHomeCobrador;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.devol.shared.TableToExcel;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIClienteImpl extends UICliente {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<ClienteProxy> lista;
	private UIHomeCliente uiHomeCliente;
	private UIHomePrestamo uiHomePrestamo;
	private UIHomeCobrador uiHomeCobrador;
	private Boolean formSelected=false;
	private Boolean isSelectedMulti=false;

	public UIClienteImpl(UIHomeCliente uiHomeCliente) {
		this.uiHomeCliente = uiHomeCliente;
		header.setVisible(false);
		toolBar.setVisible(true);
		this.isHomeCliente=true;
		this.isHomeCobranza=false;
		this.isHomePrestamo=false;
		this.toolBar.getBtnAtras().setVisible(false);
		this.toolBar.getBtnEliminar().setVisible(true);
	}
	
	public UIClienteImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		header.setVisible(true);
		toolBar.setVisible(false);
		this.isHomeCliente=false;
		this.isHomeCobranza=false;
		this.isHomePrestamo=true;
		this.toolBar.getBtnAtras().setVisible(false);
		this.toolBar.getBtnEliminar().setVisible(false);
	}
	
	public UIClienteImpl(UIHomeCobrador uiHomeCobrador,Boolean formSelected,Boolean isSelectedMulti) {
		this.uiHomeCobrador = uiHomeCobrador;
		header.setVisible(false);
		toolBar.setVisible(true);
		this.formSelected=formSelected;
		this.isSelectedMulti=isSelectedMulti;
		header.setVisible(formSelected);
		toolBar.setVisible(!formSelected);
		this.isHomeCliente=false;
		this.isHomeCobranza=true;
		this.isHomePrestamo=false;
		this.toolBar.getBtnAtras().setVisible(true);
		this.toolBar.getBtnEliminar().setVisible(false);
		this.toolBar.getBtnEditar().getUpFace().setImage(new Image(MyResource.INSTANCE.getImgDesactivar32()));
		this.toolBar.getBtnEditar().setTitle("Desactivar");
	}
	
	
	@Override
	public void goToUiSelectClient(String modo) {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			uiHomeCobrador.getHeader().getLblTitulo().setText(constants.clientes());
			uiHomeCobrador.getHeader().setVisibleBtnMenu(false);
			uiHomeCobrador.getContainer().showWidget(3);			
			uiHomeCobrador.getUiClienteSelected().cargarClienteSinCobrador();
			uiHomeCobrador.getUiClienteSelected().setBeanGestorCobranza(beanGestorCobranza);
			uiHomeCobrador.getUiClienteSelected().grid.getMultiSelectionModel().clear();
		}
	}
	
	

	@Override
	public void goToUICMantCliente(String modo) {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			uiHomeCliente.getUIMantCliente().setModo(modo);
			uiHomeCliente.getUIMantCliente().activarCampos();
			uiHomeCliente.getUIMantCliente().setTituloOperacion("Insertar");
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
			uiHomeCliente.getUIMantCliente().setTituloOperacion("Actualizar");
			uiHomeCliente.getContainer().showWidget(1);			
			uiHomeCliente.getUIMantCliente().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoDesactivar())) {
			ClienteProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccioneCliente(), null);
				//Window.alert(constants.seleccioneCliente());
				Notification not=new Notification(Notification.ALERT,constants.seleccioneCliente());
				not.showPopup();
				return;
			}				
			uiHomeCobrador.getUIMantCliente().setModo(modo);
			uiHomeCobrador.getUIMantCliente().setBean(bean);
			uiHomeCobrador.getUIMantCliente().activarCampos();
			uiHomeCobrador.getUIMantCliente().setTituloOperacion("Desactivar");
			uiHomeCobrador.getContainer().showWidget(4);			
			uiHomeCobrador.getUIMantCliente().refreshScroll();
		}else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
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
			uiHomeCliente.getUIMantCliente().setTituloOperacion("Eliminar");
			uiHomeCliente.getContainer().showWidget(1);			
			uiHomeCliente.getUIMantCliente().refreshScroll();
		}

	}

	@Override
	public void cargarTabla() {
		popup.showPopup();
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
				lista.addAll(response);
				//Window.alert(lista.get(0).getBeanUsuario().getCorreo());				
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
	}	
	
	@Override
	public void cargarClienteSinCobrador() {
		// TODO Auto-generated method stub
		popup.showPopup();
		if(UIHomeSesion.usuario!=null){
		lista = new ArrayList<ClienteProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionCobranza context = FACTORY.contextGestionCobranza();
		Request<List<ClienteProxy>> request = context.listarClienteSinCobrador(UIHomeSesion.usuario.getIdUsuario()).with("beanUsuario");
		request.fire(new Receiver<List<ClienteProxy>>() {
			@Override
			public void onSuccess(List<ClienteProxy> response) {
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
	}
	
	
	@Override
	public void cargarClientesAsignados() {
		popup.showPopup();
		if(beanGestorCobranza!=null){
		lista = new ArrayList<ClienteProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionCobranza context = FACTORY.contextGestionCobranza();
		Request<List<ClienteProxy>> request = context.listarGestorClienteByGestorCobranza(beanGestorCobranza.getIdGestorCobranza());
		request.fire(new Receiver<List<ClienteProxy>>() {
			@Override
			public void onSuccess(List<ClienteProxy> response) {				
				lista.addAll(response);
				grid.setData(lista);				
				grid.getSelectionModel().clear();
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
	public void asignarClienteAcobrador() {
		// TODO Auto-generated method stub		
		Set<ClienteProxy> lista=grid.getMultiSelectionModel().getSelectedSet();		
		if(!lista.isEmpty()){
			FACTORY.initialize(EVENTBUS);
			ContextGestionCobranza context = FACTORY.contextGestionCobranza();			
			Request<Boolean> request=context.asignarClientesAlCobrador(lista, beanGestorCobranza.getIdUsuarioOwner(), beanGestorCobranza.getIdUsuarioCobrador() , beanGestorCobranza.getIdGestorCobranza());
			request.fire(new Receiver<Boolean>(){

				@Override
				public void onSuccess(Boolean response) {
					// TODO Auto-generated method stub
					goToBack();
				}});
		}else{
			Notification not=new Notification(Notification.ALERT,constants.seleccioneCliente());
			not.showPopup();
		}
	}
	
	@Override
	public void goToBack() {
		// TODO Auto-generated method stub
		if(uiHomePrestamo!=null){
		uiHomePrestamo.getHeader().getLblTitulo().setText(constants.prestamos());
		uiHomePrestamo.getHeader().setVisibleBtnMenu(true);
		uiHomePrestamo.getContainer().showWidget(1);
		}else if(uiHomeCobrador!=null){											
			uiHomeCobrador.getContainer().showWidget(2);
			uiHomeCobrador.getUiClienteImpl().cargarClientesAsignados();
			uiHomeCobrador.getUiClienteImpl().reloadTitleCobrador();
		}
	}
	
	@Override
	public void goToUiCobrador() {
		// TODO Auto-generated method stub
		if(uiHomeCobrador!=null){
			uiHomeCobrador.getHeader().getLblTitulo().setText(constants.cobrador());
			uiHomeCobrador.getHeader().setVisibleBtnMenu(true);
			uiHomeCobrador.getContainer().showWidget(0);
			}
	}

	@Override
	public void selecciona() {
		// TODO Auto-generated method stub		
		ClienteProxy bean = grid.getSelectionModel().getSelectedObject();
		if(bean!=null){				
		uiHomePrestamo.getHeader().getLblTitulo().setText("Devolpay");
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
	
	@Override
	public void setBeanGestorCobranza(GestorCobranzaProxy beanGestorCobranza) {
		this.beanGestorCobranza = beanGestorCobranza;		
		uiHomeCobrador.getHeader().getLblTitulo().setText("Cobrador: "+beanGestorCobranza.getBeanUsuarioCobrador().getNombres()+" "+beanGestorCobranza.getBeanUsuarioCobrador().getApellidos());
	}
	
	
	public void reloadTitleCobrador(){
		uiHomeCobrador.getHeader().getLblTitulo().setText("Cobrador: "+this.beanGestorCobranza.getBeanUsuarioCobrador().getNombres()+" "+beanGestorCobranza.getBeanUsuarioCobrador().getApellidos());
	}
	
	@Override
	public void exportarData() {
		// TODO Auto-generated method stub
		Date date=new Date();
		String nameFile="client-"+date.getTime()+".xls";
		TableToExcel.save(grid,nameFile);
	}
}
