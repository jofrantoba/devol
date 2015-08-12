package com.devol.client.view.uihome;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.devol.client.model.PanelFlow;
import com.devol.client.resource.MyResource;
import com.devol.client.view.uiiniciarsesion.UIIniciarSesionImpl;
import com.devol.client.view.uiregistrarusuario.UIRegistrarUsuarioImpl;

public class UIHome extends Composite implements InterUIHome{

	private PanelFlow main;
	//public static AnimationHelper animationHelper;
	private DeckPanel container;
	private UIIniciarSesionImpl uiIniciarSesionImpl;
	private UIRegistrarUsuarioImpl uiRegistrarUsuario;
	//private UIHomeSesion uiHomeSesion;
	//private UIMenuImpl uiMenuImpl;
	private Presenter presenter;
	private String uiToken;

	public UIHome() {
		init();
		style();
	}

	private void init() {		
		// TODO Auto-generated method stub
		main = new PanelFlow();
		initWidget(main);
		
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		main.add(container);
		
		uiIniciarSesionImpl = new UIIniciarSesionImpl(this);
		container.add(uiIniciarSesionImpl);
		
		/*animationHelper = new AnimationHelper();
		animationHelper.getElement().setId("animationHelper");
		container.add(animationHelper);
		uiMenuImpl=new UIMenuImpl();
		
		animationHelper.goTo(uiMenuImpl, Animation.SLIDE_REVERSE);*/
		
		uiRegistrarUsuario = new UIRegistrarUsuarioImpl(this);
		container.add(uiRegistrarUsuario);
		
		/*uiHomeSesion = new UIHomeSesion();
		container.add(uiHomeSesion);*/
		
		container.showWidget(0);
		uiIniciarSesionImpl.scrollPanel.refresh();
	}

	private void style() {
		Window.setMargin("0px");
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		main.addStyleName(MyResource.INSTANCE.getStlUIHome().main());
		//animationHelper.addStyleName(MyResource.INSTANCE.getStlUIHome().animationHelper());
		
		// animationHelper.addStyleName(MyResource.INSTANCE.getStlUIHome().animationHelper());
		// main.getElement().getStyle().setBackgroundColor("#C3CEFA");
		// animationHelper.getElement().getStyle().setBackgroundColor("#d3d6db");
	}
	
	public DeckPanel getContainer() {
		return container;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		this.presenter=presenter;
	}

	@Override
	public void setUIToken(String token) {
		// TODO Auto-generated method stub
		this.uiToken=token;
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public UIIniciarSesionImpl getUiIniciarSesionImpl() {
		return uiIniciarSesionImpl;
	}

	public UIRegistrarUsuarioImpl getUiRegistrarUsuario() {
		return uiRegistrarUsuario;
	}
	
	

	/*public UIHomeSesion getUiHomeSesion() {
		return uiHomeSesion;
	}*/

	/*public UIMenuImpl getUiMenuImpl() {
		return uiMenuImpl;
	}*/
	
	
	

}
