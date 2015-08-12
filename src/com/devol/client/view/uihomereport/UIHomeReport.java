package com.devol.client.view.uihomereport;

import java.util.Date;

import com.devol.client.model.UIHomeHeader;
import com.devol.client.view.uirecaudado.UIRecaudadoImpl;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class UIHomeReport extends Composite {
	private FlowPanel main;
	private UIHomeHeader header;
	private DeckPanel container;
	private UIRecaudadoImpl uiRecaudado;

	public UIHomeReport() {		
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

		uiRecaudado = new UIRecaudadoImpl(this);
		container.add(uiRecaudado);
		DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
		Date fechaIni=new Date();
		String fecha=format.format(fechaIni);		
		fechaIni=format.parse(fecha);
		uiRecaudado.cargarTabla(fechaIni);
		container.showWidget(0);	
		uiRecaudado.scrollPanel.refresh();
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

	public UIHomeHeader getHeader() {
		return header;
	}

	public UIRecaudadoImpl getUiRecaudado() {
		return uiRecaudado;
	}
	
	
	
}

