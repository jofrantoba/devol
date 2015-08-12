package com.devol.client.view.uihomeprestamo;

import com.devol.client.model.UIHomeHeader;
import com.devol.client.view.uiamortizacion.UIAmortizacionImpl;
import com.devol.client.view.uicliente.UIClienteImpl;
import com.devol.client.view.uimantamortizacion.UIMantAmortizacionImpl;
import com.devol.client.view.uimantprestamo.UIMantPrestamoImpl;
import com.devol.client.view.uiprestamo.UIPrestamoImpl;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class UIHomePrestamo extends Composite {
	private FlowPanel main;
	private UIHomeHeader header;
	private DeckPanel container;
	private UIPrestamoImpl uiPrestamo;
	private UIMantPrestamoImpl uiMantPrestamo;
	private UIClienteImpl uiCliente;
	private UIAmortizacionImpl uiAmortizacion;
	private UIMantAmortizacionImpl uiMantAmortizacion;
	private String modo;

	public UIHomePrestamo(String modo) {
		this.modo=modo;
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

		uiPrestamo = new UIPrestamoImpl(this);
		container.add(uiPrestamo);

		uiMantPrestamo = new UIMantPrestamoImpl(this);
		container.add(uiMantPrestamo);
		
		uiCliente=new UIClienteImpl(this);
		container.add(uiCliente);
		
		uiAmortizacion = new UIAmortizacionImpl(this);
		container.add(uiAmortizacion);
		
		uiMantAmortizacion=new UIMantAmortizacionImpl(this);
		container.add(uiMantAmortizacion);
		
		container.showWidget(0);
		uiPrestamo.scrollPanel.refresh();
	}

	private void style() {
		// TODO Auto-generated method stub
		Window.setMargin("0px");
		/*
		 * int height = Window.getClientHeight(); container.setHeight((height -
		 * 42) + "px");
		 */
		// main.getElement().getStyle().setBackgroundColor("#d3d6db");
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UIPrestamoImpl getUIPrestamoImpl() {
		return uiPrestamo;
	}

	public UIMantPrestamoImpl getUIMantPrestamoImpl() {
		return uiMantPrestamo;
	}		
		

	public UIAmortizacionImpl getUiAmortizacionImpl() {
		return uiAmortizacion;
	}

	public UIMantAmortizacionImpl getUiMantAmortizacionImpl() {
		return uiMantAmortizacion;
	}

	public UIClienteImpl getUiCliente() {
		return uiCliente;
	}

	public UIHomeHeader getHeader() {
		return header;
	}

	public String getModo() {
		return modo;
	}
	
	
	

}
