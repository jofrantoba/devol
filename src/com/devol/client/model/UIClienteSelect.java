package com.devol.client.model;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.grid.GridCliente;
import com.devol.client.requestfactory.ContextGestionCliente;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.resource.MyResource;
import com.devol.client.view.uimantprestamo.UIMantPrestamo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class UIClienteSelect extends PopupPanel implements ValueChangeHandler,TouchEndHandler,ResizeHandler {

	private FlowPanel main;
	private MSearchBox txtBuscar;
	private ScrollPanel scrollPanel;
	private GridCliente grid;
	private HeaderGrid headerGrid;
	private Label headerGridDni;
	private Label headerGridCliente;
	private FlowPanel container;
	private HorizontalPanel footer;
	private Button btnAceptar;
	private Button btnCancelar;
	private UIMantPrestamo padre;

	public UIClienteSelect(UIMantPrestamo padre) {
		this.padre = padre;
		init();
		initWidgetListener();
		style();

	}

	private void init() {
		setModal(true);
		setGlassEnabled(true);
		setAnimationEnabled(true);
		main = new FlowPanel();
		setWidget(main);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder("Buscar Cliente");
		main.add(txtBuscar);

		headerGrid = new HeaderGrid();
		main.add(headerGrid);

		headerGridDni = new Label("DNI");
		headerGrid.add(headerGridDni);

		headerGridCliente = new Label("CLIENTE");
		headerGrid.add(headerGridCliente);

		container = new FlowPanel();
		main.add(container);

		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setAutoHandleResize(true);
		scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());
		container.add(scrollPanel);

		grid = new GridCliente();
		scrollPanel.setWidget(grid);
		
		footer = new HorizontalPanel();
		main.add(footer);
		
		btnAceptar = new Button("Aceptar");
		footer.add(btnAceptar);
		
		btnCancelar = new Button("Cancelar");
		footer.add(btnCancelar);
		
		
		cargarTabla();
	}

	@SuppressWarnings("unchecked")
	private void initWidgetListener() {
		txtBuscar.addValueChangeHandler(this);		
		btnAceptar.addTouchEndHandler(this);
		btnCancelar.addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUICliente().ensureInjected();

		main.setWidth("100%");
		headerGridDni.setWidth("40%");
		headerGridCliente.setWidth("60%");
		setHeightContainer(168);
		scrollPanel.setWidth("100%");
		scrollPanel.setHeight("100%");
		this.footer.setWidth("100%");
		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUICliente()
				.txtBuscarUICliente());
		grid.addStyleName(MyResource.INSTANCE.getStlUICliente().gridUICliente());

		headerGridDni.getElement().getStyle().setFloat(Style.Float.LEFT);
		headerGridCliente.getElement().getStyle().setFloat(Style.Float.LEFT);

	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		container.setHeight((height - heightHeader) + "px");
	}

	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<ClienteProxy> lista;

	public void cargarTabla() {
		lista = new ArrayList<ClienteProxy>();
		/*
		 * for (int i = 0; i < 20; i++) { Cliente bean = new Cliente();
		 * bean.setDni("444857" + i); bean.setNombre("Cliente");
		 * bean.setApellido("" + i); lista.add(bean); }
		 */
		FACTORY.initialize(EVENTBUS);
		ContextGestionCliente context = FACTORY.contextGestionCliente();
		Request<List<ClienteProxy>> request = context.listarCliente();
		request.fire(new Receiver<List<ClienteProxy>>() {
			@Override
			public void onSuccess(List<ClienteProxy> response) {
				// TODO Auto-generated method stub
				lista = response;
				grid.setData(lista);
				//grid.getSelectionModel().clear();
				scrollPanel.refresh();
			}
		});

	}

	@Override
	public void onValueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		filtrar();
	}

	private void filtrar() {
		grid.getDataProvider().setFilter(txtBuscar.getText());
		if (grid.getDataProvider().hasFilter()) {
			int alto = grid.getDataProvider().resulted.size() * 15;
			// grid.setHeight(alto + "mm");
		} else {
			int alto = grid.getData().size() * 15;
			// grid.setHeight(alto + "mm");
		}
		grid.getDataProvider().refresh();
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		//setHeightDialog();
		setHeightContainer(168);
	}
	
	public void setHeightDialog(){
		//int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		//this.setWidth((width-60)+"px");
		//main.setHeight((height-40)+"px");
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(btnAceptar)){
			ClienteProxy bean =null;/*= grid.getSelectionModel().getSelectedObject();*/
			if (bean == null)
				return;
			//padre.getTxtCliente().setText(bean.getNombre()+" "+bean.getApellido());
			padre.setBeanCliente(bean);
			this.hide();
		}else if(event.getSource().equals(btnCancelar)){
			this.hide();
		}
	}
}
