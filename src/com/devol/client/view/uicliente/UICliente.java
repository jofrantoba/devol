package com.devol.client.view.uicliente;



import com.devol.client.grid.GridCliente;
import com.devol.client.model.HeaderGrid;
import com.devol.client.model.HeaderMenu;
import com.devol.client.model.ToolBar;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class UICliente extends Composite implements InterUICliente,
		ValueChangeHandler, ClickHandler{
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private MSearchBox txtBuscar;
	protected HeaderMenu header;
	private Label lblCenter;
	protected ToolBar toolBar;
	public ScrollPanel scrollPanel;
	protected GridCliente grid;
	private HeaderGrid headerGrid;
	private Label headerGridDni;
	private Label headerGridCliente;
	protected FlowPanel container;
	private PushButton btnBack;
	private PushButton btnSelect;
	//private WidgetList widgetListTable;

	public UICliente() {
		init();
		initWidgetListener();
		style();		
		reCalcularWindows();
	}

	private void init() {
		//widgetListTable=new WidgetList();
		//widgetListTable.setRound(true);
		main = new FlowPanel();
		
		//Window.addResizeHandler(this);
		header = new HeaderMenu();		
		lblCenter = new Label(constants.seleccionar());
		header.setCenterWidget(lblCenter);
		btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
		header.setLeftWidget(btnBack);
		btnSelect = new PushButton(new Image(MyResource.INSTANCE.getImgSelect32()));
		header.setRightWidget(btnSelect);
		main.add(header);			
		toolBar = new ToolBar();
		main.add(toolBar);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder(constants.buscarCliente());
		main.add(txtBuscar);

		headerGrid = new HeaderGrid();
		main.add(headerGrid);

		headerGridDni = new Label(constants.dni());
		headerGrid.add(headerGridDni);

		headerGridCliente = new Label(constants.clientes());
		headerGrid.add(headerGridCliente);

		container = new FlowPanel();		
		//main.add(container);

		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);

		/*scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setAutoHandleResize(true);*/
		//scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());		

		grid = new GridCliente();		
		container.add(grid);		
		//widgetListTable.add(container);
		scrollPanel.setWidget(container);
		//cargarTabla();
		main.add(scrollPanel);				
		Window.addResizeHandler(new ResizeHandler(){

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}});
		initWidget(main);		
	}
	
	private void reCalcularWindows(){
		setWidthGrid();
		setHeightContainer(180);				
	}

	@SuppressWarnings("unchecked")
	private void initWidgetListener() {
		txtBuscar.addValueChangeHandler(this);		
		toolBar.getBtnNuevo().addClickHandler(this);
		toolBar.getBtnEditar().addClickHandler(this);
		toolBar.getBtnEliminar().addClickHandler(this);
		toolBar.getBtnActualizar().addClickHandler(this);
		btnSelect.addClickHandler(this);
		btnBack.addClickHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUICliente().ensureInjected();
		btnSelect.addStyleName(MyResource.INSTANCE.getStlUICliente().pushButton());
		btnBack.addStyleName(MyResource.INSTANCE.getStlUICliente().pushButton());
		//main.setWidth("100%");
		headerGridDni.setWidth("40%");
		headerGridCliente.setWidth("60%");
		//setHeightContainer(55);
		//setWidthGrid();
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("auto");

		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUICliente()
				.txtBuscarUICliente());
		grid.addStyleName(MyResource.INSTANCE.getStlUICliente().gridUICliente());

		headerGridDni.getElement().getStyle().setFloat(Style.Float.LEFT);
		headerGridCliente.getElement().getStyle().setFloat(Style.Float.LEFT);

	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		scrollPanel.setHeight((height - heightHeader) + "px");
		this.scrollPanel.refresh();
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

	//@Override
	//public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		//setWidthGrid();
		//setHeightContainer(55);
		//this.scrollPanel.refresh();
		//reCalcularWindows();
	//}

	private void setWidthGrid() {
		int width = Window.getClientWidth();
		width = width - 20;
		// grid.setWidth(width + "px");
	}
	
	@Override
	public void goToUICMantCliente(String modo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cargarTabla() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToBack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecciona() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(toolBar.getBtnNuevo())) {
			//Window.alert("ok");
			goToUICMantCliente(constants.modoNuevo());
		}else if (event.getSource().equals(toolBar.getBtnEditar())) {
			goToUICMantCliente(constants.modoEditar());
		}else if (event.getSource().equals(toolBar.getBtnEliminar())) {
			goToUICMantCliente(constants.modoEliminar());
		}else if(event.getSource().equals(toolBar.getBtnActualizar())){
			cargarTabla();
		}else if (event.getSource().equals(btnBack)) {
			goToBack();
		}else if (event.getSource().equals(btnSelect)) {
			selecciona();
		}
	}
}
