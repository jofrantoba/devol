package com.devol.client.view.uihomecobrador;

import com.devol.client.model.UIHomeHeader;
import com.devol.client.view.uicliente.UIClienteImpl;
import com.devol.client.view.uicobrador.UICobradorImpl;
import com.devol.client.view.uimantcliente.UIMantClienteImpl;
import com.devol.client.view.uimantcobrador.UIMantCobradorImpl;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class UIHomeCobrador extends Composite {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private UIHomeHeader header;
	private DeckPanel container;
	private UICobradorImpl uiCobrador;	
	private UIMantCobradorImpl uiMantCobrador;
	private UIClienteImpl uiCliente;
	private UIClienteImpl uiClienteSelected;
	private UIMantClienteImpl uiMantCliente;

	public UIHomeCobrador() {
		init();
		style();		
	}
		

	private void init() {
		// TODO Auto-generated method stub
		main = new FlowPanel();
		initWidget(main);

		header = new UIHomeHeader();
		header.getLblTitulo().setText(constants.cobrador());
		main.add(header);

		container = new DeckPanel();
		container.setAnimationEnabled(true);
		main.add(container);

		uiCobrador = new UICobradorImpl(this);		
		container.add(uiCobrador);

		uiMantCobrador = new UIMantCobradorImpl(this);
		container.add(uiMantCobrador);
		
		uiCliente=new UIClienteImpl(this,false,false);
		container.add(uiCliente);
		
		uiClienteSelected=new UIClienteImpl(this,true,true);
		uiClienteSelected.grid.setMultiSelection();
		container.add(uiClienteSelected);
		
		uiMantCliente = new UIMantClienteImpl(this);
		container.add(uiMantCliente);
		
		container.showWidget(0);		
	}
	

	private void style() {
		// TODO Auto-generated method stub
		Window.setMargin("0px");
		/*int height = Window.getClientHeight();
		container.setHeight((height - 42) + "px");*/
		// main.getElement().getStyle().setBackgroundColor("#d3d6db");
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UIMantCobradorImpl getUIMantCobrador() {
		return uiMantCobrador;
	}

	public UICobradorImpl getUICobradorImpl() {
		return uiCobrador;
	}
	
	
	
	
	public UIMantClienteImpl getUIMantCliente() {
		return uiMantCliente;
	}


	public UIHomeHeader getHeader() {
		return header;
	}


	public UIClienteImpl getUiClienteImpl() {
		return uiCliente;
	}
	
	public void reloadTitle(){
		header.getLblTitulo().setText(constants.cobrador());
	}


	public UIClienteImpl getUiClienteSelected() {
		return uiClienteSelected;
	}
	
	
}
