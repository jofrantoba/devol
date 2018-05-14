package com.devol.client.view.uihomecobranza;

import com.devol.client.model.UIHomeHeader;
import com.devol.client.view.uiamortizacion.UIAmortizacionImpl;
import com.devol.client.view.uimantamortizacion.UIMantAmortizacionImpl;
import com.devol.client.view.uiprestamista.UIPrestamistaImpl;
import com.devol.client.view.uiprestamo.UIPrestamoImpl;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class UIHomeCobranza extends Composite {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private UIHomeHeader header;
	private DeckPanel container;
	private UIPrestamistaImpl uiPrestamista;
	//private UIPrestamoImpl uiPrestamo;		
	//private UIAmortizacionImpl uiAmortizacion;
	//private UIMantAmortizacionImpl uiMantAmortizacion;
	private String modo;

	public UIHomeCobranza(String modo) {
		this.modo=modo;
		init();
		style();		
	}
	

	private void init() {
		// TODO Auto-generated method stub
		main = new FlowPanel();
		initWidget(main);

		header = new UIHomeHeader();
		header.getLblTitulo().setText("Prestamistas");
		main.add(header);

		container = new DeckPanel();
		container.setAnimationEnabled(true);
		main.add(container);
		
		uiPrestamista = new UIPrestamistaImpl(this);		
		container.add(uiPrestamista);

		/*uiPrestamo = new UIPrestamoImpl(this);
		container.add(uiPrestamo);		
		
		uiAmortizacion = new UIAmortizacionImpl(this);
		container.add(uiAmortizacion);
		
		uiMantAmortizacion=new UIMantAmortizacionImpl(this);
		container.add(uiMantAmortizacion);*/
		
		container.showWidget(0);		
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
	
	

	public UIPrestamistaImpl getUiPrestamistaImpl() {
		return uiPrestamista;
	}


	/*public UIPrestamoImpl getUIPrestamoImpl() {
		return uiPrestamo;
	}
		

	public UIAmortizacionImpl getUiAmortizacionImpl() {
		return uiAmortizacion;
	}

	public UIMantAmortizacionImpl getUiMantAmortizacionImpl() {
		return uiMantAmortizacion;
	}*/

	public UIHomeHeader getHeader() {
		return header;
	}
	
	public void reloadTitle(){
		header.getLblTitulo().setText("Prestamistas");
	}

	public String getModo() {
		return modo;
	}
	
	
	

}
