package com.devol.client.view.uirecaudado;

import java.util.Date;

import com.devol.client.grid.GridRecaudado;
import com.devol.client.model.TextBoxCalendar;
import com.devol.client.model.ToolBar;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;

public class UIRecaudado extends Composite implements InterUIRecaudado,ValueChangeHandler, TouchEndHandler,ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private MSearchBox txtBuscar;
	protected ToolBar toolBar;
	protected GridRecaudado grid;	
	protected FlowPanel container;
	private PushButton btnFiltro;
	private HorizontalPanel pnlTotalRecaudado;
	private Label lblTotal;
	protected Label lblMontoTotal;
	protected TextBoxCalendar txtFecha;

	public UIRecaudado() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {
		txtFecha=new TextBoxCalendar();
		main = new FlowPanel();
		// Window.addResizeHandler(this);

		toolBar = new ToolBar();
		toolBar.getBtnNuevo().setVisible(false);
		toolBar.getBtnEditar().setVisible(false);
		toolBar.getBtnEliminar().setVisible(false);
		toolBar.getBtnActualizar().setVisible(false);
		main.add(toolBar);
		
		btnFiltro = new PushButton(
				new Image(MyResource.INSTANCE.getImgFiltro32()));
		btnFiltro.setTitle("Amortizar");
		toolBar.getToolbar().add(btnFiltro);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder(constants.buscarAmortizacion());		
		main.add(txtBuscar);	

		container = new FlowPanel();
	
		/*
		 * scrollPanel.setScrollingEnabledX(false);
		 * scrollPanel.setScrollingEnabledY(true);
		 * scrollPanel.setAutoHandleResize(true);
		 */
		// scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());
		// container.add(scrollPanel);

		grid = new GridRecaudado();
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
		pnlTotalRecaudado = new HorizontalPanel();
		main.add(pnlTotalRecaudado);
		lblTotal = new Label(constants.recaudado());
		pnlTotalRecaudado.add(lblTotal);

		lblMontoTotal = new Label("0.00");
		pnlTotalRecaudado.add(lblMontoTotal);
		initWidget(main);
	}
			
	

	private void reCalcularWindows() {
		setWidthGrid();
		setHeightContainer(220);
	}

	@SuppressWarnings("unchecked")
	private void initWidgetListener() {
		txtBuscar.addValueChangeHandler(this);					
		btnFiltro.addClickHandler(this);
		txtFecha.getCalendar().getBtnAceptar().addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIRecaudado().ensureInjected();
		btnFiltro.addStyleName(MyResource.INSTANCE.getStlUIRecaudado().pushButton());
		btnFiltro.getElement().getStyle().setFloat(Style.Float.RIGHT);
		btnFiltro.getElement().getStyle().setRight(4, Unit.PX);

		// main.setWidth("100%");	
		/*
		 * setHeightContainer(127); setWidthGrid(); container.setWidth("100%");
		 * scrollPanel.setWidth("100%"); scrollPanel.setHeight("100%");
		 */

		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.txtBuscarUIRecaudado());
		grid.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.gridUIRecaudado());
	
		pnlTotalRecaudado.setWidth("100%");
		pnlTotalRecaudado.setHeight("40px");
		pnlTotalRecaudado.addStyleName(MyResource.INSTANCE
				.getStlUIRecaudado().pnlTotalRecaudadoUIRecaudado());
		lblTotal.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.lblTotalUIRecaudado());
		lblMontoTotal.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.lblMontoTotalUIRecaudado());
	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		grid.setHeight((height - heightHeader) + "px");
		container.setHeight((height - heightHeader) + "px");
		grid.redraw();
	}

	@Override
	public void cargarTabla(Date fechaIni) {
		
	}

	

	@Override
	public void onValueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(txtBuscar)){
			filtrar();
		}/*else if(event.getSource().equals(txtFecha.getTxtFecha())){		
			Window.alert("Hola mundo");
			cargarTabla(txtFecha.getDate());
		}*/
		
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
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		 if(event.getSource().equals(txtFecha.getCalendar().getBtnAceptar())){
			//Window.alert(txtFecha.getText());
			cargarTabla(txtFecha.getDate());
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnFiltro)) {
			// Window.alert("ok");
			txtFecha.showCalendar();
		}
	}



}
