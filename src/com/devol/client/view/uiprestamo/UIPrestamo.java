package com.devol.client.view.uiprestamo;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.grid.GridPrestamo;
import com.devol.client.model.ToolBar;
import com.devol.client.resource.MyResource;
import com.devol.client.util.Notification;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
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
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;

public class UIPrestamo extends Composite implements InterUIPrestamo,
		ValueChangeHandler, ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private MSearchBox txtBuscar;
	protected ToolBar toolBar;	
	protected GridPrestamo grid;
	protected FlowPanel container;
	private PushButton btnAmortizacion;
	protected Boolean isHomeCobranza=false;
	protected Boolean isHomePrestamo=false;

	public UIPrestamo() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {
		main = new FlowPanel();
		// Window.addResizeHandler(this);

		toolBar = new ToolBar();
		main.add(toolBar);
		
		btnAmortizacion = new PushButton(
				new Image(MyResource.INSTANCE.getImgCash32()));
		btnAmortizacion.setTitle(constants.amortizar());
		toolBar.getToolbar().add(btnAmortizacion);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder(constants.buscarPrestamo());
		main.add(txtBuscar);

		container = new FlowPanel();
		
		/*
		 * scrollPanel.setScrollingEnabledX(false);
		 * scrollPanel.setScrollingEnabledY(true);
		 * scrollPanel.setAutoHandleResize(true);
		 */
		// scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());
		// container.add(scrollPanel);

		grid = new GridPrestamo();
		container.add(grid);
		main.add(container);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}
		});
		// cargarTabla();		
		initWidget(main);
	}

	private void reCalcularWindows() {
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
		toolBar.getBtnExportar().addClickHandler(this);
		btnAmortizacion.addClickHandler(this);
	}

	private void style() {
		Window.setMargin("0px");
		MGWT.applySettings(MGWTSettings.getAppSetting());
		MyResource.INSTANCE.getStlUIPrestamo().ensureInjected();
		
		btnAmortizacion.getElement().getStyle().setFloat(Style.Float.RIGHT);
		btnAmortizacion.getElement().getStyle().setRight(4, Unit.PX);

		// main.setWidth("100%");
		/*
		 * setHeightContainer(127); setWidthGrid(); container.setWidth("100%");
		 * scrollPanel.setWidth("100%"); scrollPanel.setHeight("100%");
		 */

		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUIPrestamo()
				.txtBuscarUIPrestamo());
		grid.addStyleName(MyResource.INSTANCE.getStlUIPrestamo()
				.gridUIPrestamo());
		
		btnAmortizacion.addStyleName(MyResource.INSTANCE.getStlUIPrestamo().pushButton());
	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		grid.setHeight((height - heightHeader) + "px");
		container.setHeight((height - heightHeader) + "px");
		grid.redraw();
	}

	@Override
	public void cargarTabla() {
		/*
		 * List<Prestamo> lista = new ArrayList<Prestamo>(); for (int i = 0; i <
		 * 10; i++) { Prestamo bean = new Prestamo(); bean.setIdPrestamo("" + (i
		 * + 1)); bean.setFecha(new Date());
		 * 
		 * Cliente clie = new Cliente(); clie.setNombre("Cliente");
		 * clie.setApellido("" + i); bean.setBeanCliente(clie); lista.add(bean);
		 * }
		 * 
		 * grid.setData(lista); grid.getSelectionModel().clear();
		 * scrollPanel.refresh();+
		 */
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

	/*
	 * @Override public void onResize(ResizeEvent event) { // TODO
	 * Auto-generated method stub setWidthGrid(); setHeightContainer(127); }
	 */

	private void setWidthGrid() {
		int width = Window.getClientWidth();
		width = width - 20;
		grid.setWidth(width + "px");
	}

	

	@Override
	public void goToUIMantPrestamo(String modo) {

	}
	
	@Override
	public void goToUIAmortizacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(toolBar.getBtnNuevo())) {
			// Window.alert("ok");
			goToUIMantPrestamo(constants.modoNuevo());
		} else if (event.getSource().equals(toolBar.getBtnEditar())) {
			goToUIMantPrestamo(constants.modoEditar());
		} else if (event.getSource().equals(toolBar.getBtnEliminar())) {
			goToUIMantPrestamo(constants.modoEliminar());
		} else if (event.getSource().equals(toolBar.getBtnActualizar())) {
			if(this.isHomePrestamo){
				cargarTabla();
			}else if(this.isHomeCobranza){
				cargarPrestamoGestorCobranza();
			}
		}else if (event.getSource().equals(this.btnAmortizacion)) {
			PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccionPrestamo(), null);
				//Window.alert(constants.seleccionPrestamo());
				Notification not=new Notification(Notification.ALERT,constants.seleccionPrestamo());
				not.showPopup();
				return;
			}
			goToUIAmortizacion();
		}else if(event.getSource().equals(toolBar.getBtnExportar())){
			exportarData();
		}
	}

	@Override
	public boolean sendPrestamoHistorial(String idPrestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cargarPrestamoGestorCobranza() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportarData() {
		// TODO Auto-generated method stub
		
	}

}
