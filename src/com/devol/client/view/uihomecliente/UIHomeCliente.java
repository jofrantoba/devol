package com.devol.client.view.uihomecliente;

import com.devol.client.model.UIHomeHeader;
import com.devol.client.view.uicliente.UIClienteImpl;
import com.devol.client.view.uimantcliente.UIMantClienteImpl;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;

public class UIHomeCliente extends Composite {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private UIHomeHeader header;
	private DeckPanel container;
	private UIClienteImpl uiCliente;
	private UIMantClienteImpl uiMantCliente;

	public UIHomeCliente() {
		init();
		style();		
	}
		

	private void init() {
		// TODO Auto-generated method stub
		main = new FlowPanel();
		initWidget(main);

		header = new UIHomeHeader();
		header.getLblTitulo().setText(constants.clientes());
		main.add(header);

		container = new DeckPanel();
		container.setAnimationEnabled(true);
		main.add(container);

		uiCliente = new UIClienteImpl(this);		
		container.add(uiCliente);

		uiMantCliente = new UIMantClienteImpl(this);
		container.add(uiMantCliente);			
		container.showWidget(0);		
	}
	

	private void style() {
		// TODO Auto-generated method stub
		Window.setMargin("0px");
		MGWT.applySettings(MGWTSettings.getAppSetting());
		/*int height = Window.getClientHeight();
		container.setHeight((height - 42) + "px");*/
		// main.getElement().getStyle().setBackgroundColor("#d3d6db");
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UIMantClienteImpl getUIMantCliente() {
		return uiMantCliente;
	}

	public UIClienteImpl getUIClienteImpl() {
		return uiCliente;
	}


	public UIHomeHeader getHeader() {
		return header;
	}	
	
	
	
}
