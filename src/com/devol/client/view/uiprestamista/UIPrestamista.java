package com.devol.client.view.uiprestamista;

import com.devol.client.grid.GridPrestamista;
import com.devol.client.model.ToolBar;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;

public class UIPrestamista extends Composite implements InterUIPrestamista, ValueChangeHandler, ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private MSearchBox txtBuscar;
	protected ToolBar toolBar;
	protected GridPrestamista grid;
	protected FlowPanel container;
	private PushButton btnVerPrestamos;	

	public UIPrestamista() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {		
		main = new FlowPanel();				
		toolBar = new ToolBar();			
		btnVerPrestamos=new PushButton(new Image(MyResource.INSTANCE.getImgPrestamo32()));
		btnVerPrestamos.setTitle("Ver Prestamos");
		toolBar.getToolbar().add(btnVerPrestamos);
		main.add(toolBar);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder(constants.buscarPrestamista());
		main.add(txtBuscar);

		container = new FlowPanel();
		grid = new GridPrestamista();
		container.add(grid);
		main.add(container);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}
		});
		initWidget(main);
	}

	private void reCalcularWindows() {
		setWidthGrid();
		setHeightContainer(180);
	}

	@SuppressWarnings("unchecked")
	private void initWidgetListener() {
		txtBuscar.addValueChangeHandler(this);
		toolBar.getBtnNuevo().setVisible(false);
		toolBar.getBtnEditar().setVisible(false);		
		toolBar.getBtnEliminar().setVisible(false);
		toolBar.getBtnActualizar().addClickHandler(this);	
		toolBar.getBtnExportar().addClickHandler(this);
		btnVerPrestamos.addClickHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUICliente().ensureInjected();	
		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUICliente().txtBuscarUICliente());
		grid.addStyleName(MyResource.INSTANCE.getStlUICliente().gridUICliente());
		btnVerPrestamos.getElement().getStyle().setFloat(Style.Float.RIGHT);
		btnVerPrestamos.getElement().getStyle().setRight(4, Unit.PX);
		btnVerPrestamos.addStyleName(MyResource.INSTANCE.getStlUIPrestamo().pushButton());
	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		grid.setHeight((height - heightHeader) + "px");
		container.setHeight((height - heightHeader) + "px");
		grid.redraw();
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

	private void setWidthGrid() {
		int width = Window.getClientWidth();
		width = width - 20;
		grid.setWidth(width + "px");
	}
	

	@Override
	public void onClick(ClickEvent event) {	
		if (event.getSource().equals(toolBar.getBtnActualizar())) {
			cargarTabla();
		}else if(event.getSource().equals(btnVerPrestamos)){
			goToUiPrestamo();
		}else if(event.getSource().equals(toolBar.getBtnExportar())){
			exportarData();
		}
	}

	@Override
	public void cargarTabla() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUiPrestamo() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void exportarData() {
		// TODO Auto-generated method stub
		
	}


}
