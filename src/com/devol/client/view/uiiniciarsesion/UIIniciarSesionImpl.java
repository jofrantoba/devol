package com.devol.client.view.uiiniciarsesion;

import com.devol.client.service.ServiceGestionUsuario;
import com.devol.client.service.ServiceGestionUsuarioAsync;
import com.devol.client.util.Notification;
import com.devol.client.view.uihome.UIHome;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.devol.server.model.bean.UsuarioRPC;

public class UIIniciarSesionImpl extends UIIniciarSesion {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final ServiceGestionUsuarioAsync SERVICE = GWT
			.create(ServiceGestionUsuario.class);
	private UIHome uiHome;
	
	public UIIniciarSesionImpl(UIHome uiHome) {
		this.uiHome = uiHome;
		this.scrollPanel.refresh();			
	}
	

	@Override
	public void login() {
		// TODO Auto-generated method stub
		SERVICE.logearUsuario(this.txtCorreo.getText(),
				this.txtClave.getText(), new AsyncCallback<UsuarioRPC>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						//Window.alert(caught.getMessage());
						//Window.alert(constants.usuarioClaveIncorrecto());
						Notification not=new Notification(Notification.ALERT,constants.usuarioClaveIncorrecto());
						not.showPopup();
					}

					@Override
					public void onSuccess(UsuarioRPC result) {
						// TODO Auto-generated method stub
						if(result != null){
							UIHomeSesion.usuario=result;							
							//Dialogs.alert("Login",constants.bienvenido()+" "+UIHomeSesion.usuario.getNombres(), null);
							//Window.alert(constants.bienvenido()+" "+UIHomeSesion.usuario.getNombres());							
							irCuenta();
							Notification not=new Notification(Notification.INFORMATION,constants.bienvenido()+" "+UIHomeSesion.usuario.getNombres());
							not.showPopup();
						}else{
							//Window.alert(constants.usuarioClaveIncorrecto());
							Notification not=new Notification(Notification.ALERT,constants.usuarioClaveIncorrecto());
							not.showPopup();
						}
					}
				});
	}
	
	@Override
	public void irCuenta() {
		// TODO Auto-generated method stub		
		/*uiHome.getContainer().showWidget(1);
		uiHome.getUiMenuImpl().refreshScroll();*/
		//uiHome.getContainer().showWidget(2);
		/*UIHomeSesion.animationHelper.goTo(new UIMenuImpl(),
				Animations.SLIDE_REVERSE);*/
		RootPanel.get().clear();
		RootPanel.get().add(UIHomeSesion.getUihomesesion());
		UIHomeSesion.getUihomesesion().getUiMenuImpl();		
	}	
	
	@Override
	public void irCrearCuenta() {
		// TODO Auto-generated method stub
		uiHome.getContainer().showWidget(1);	
		uiHome.getUiRegistrarUsuario().scrollPanel.refresh();
	}
}
