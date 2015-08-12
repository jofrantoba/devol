package com.devol.client.view.uihomecliente;

import com.devol.client.model.UIHomeHeader;
import com.devol.client.view.uicliente.UIClienteImpl;
import com.devol.client.view.uimantcliente.UIMantClienteImpl;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class UIHomeCliente extends Composite {
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
		main.add(header);

		container = new DeckPanel();
		container.setAnimationEnabled(true);
		main.add(container);

		uiCliente = new UIClienteImpl(this);		
		container.add(uiCliente);

		uiMantCliente = new UIMantClienteImpl(this);
		container.add(uiMantCliente);			
		container.showWidget(0);
		uiCliente.scrollPanel.refresh();
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

	public UIMantClienteImpl getUIMantCliente() {
		return uiMantCliente;
	}

	public UIClienteImpl getUIClienteImpl() {
		return uiCliente;
	}
}
