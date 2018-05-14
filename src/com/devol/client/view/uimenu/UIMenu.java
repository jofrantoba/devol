package com.devol.client.view.uimenu;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.devol.client.cell.MenuCell;
import com.devol.client.resource.MyResource;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.client.model.UIHomeHeader;
import com.devol.client.model.UIMenuHeader;
import com.devol.i18n.DevolConstants;
import com.devol.server.model.bean.UsuarioRPC;

public class UIMenu extends Composite implements InterUIMenu,ResizeHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private UIHomeHeader headerHome;
	private UIMenuHeader header;
	private FlowPanel container;
	protected CellList<String> cellList;
	public ScrollPanel scrollPanel;
	protected final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	private List<String> lista = new ArrayList<String>();

	public UIMenu() {
		inti();
		style();
	}

	private void inti() {
		// TODO Auto-generated method stub
		main = new FlowPanel();
		initWidget(main);
		Window.addResizeHandler(this);
		headerHome = new UIHomeHeader();
		headerHome.setVisibleBtnMenu(false);
		main.add(headerHome);

		header = new UIMenuHeader();
		header.setCenterWidget(new Label(constants.menu()));
		main.add(header);

		container = new FlowPanel();
		//main.add(container);

		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);		
		//scrollPanel.setScrollingEnabledY(true);
		//container.add(scrollPanel);		
		cellList = new CellList<String>(new MenuCell());		
	    cellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
	    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
	    render();
		//cellList.setRound(true);

		//cellList.addCellSelectedHandler(cellSelectedHandler);		
		//scrollPanel.setWidget(cellList);
		container.add(cellList);
		scrollPanel.setWidget(container);
		main.add(scrollPanel);		
		/*Window.addResizeHandler(new ResizeHandler(){

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}});*/
		
		// Add a selection model so we can select cells.	    
	    cellList.setSelectionModel(selectionModel);	    
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub	
				if(selectionModel!=null && selectionModel.getSelectedObject()!=null){
					viewMenuItem(selectionModel.getSelectedObject());
				}								
			}
		});
	    
	}
	
	
	/*private void setWidthGrid() {
		int width = Window.getClientWidth();
		width = width - 20;
		// grid.setWidth(width + "px");
	}*/
	
	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		scrollPanel.setHeight((height - heightHeader) + "px");
		this.scrollPanel.refresh();
	}

	private void render() {		
				lista.add(constants.clientes());
				lista.add(constants.cobrador());
				lista.add(constants.cobranza());
				lista.add(constants.prestamos());
				lista.add(constants.historial());
				lista.add(constants.reportes());		
				lista.add(constants.salir());						
				cellList.setRowData(lista);		
	}
		



	private void style() {
		// TODO Auto-generated method stub
		MyResource.INSTANCE.getStlUIMenu().ensureInjected();
		Window.setMargin("0px");
		setHeightContainer(105);
		//container.setHeight((height - 84) + "px");
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		//cellList.addStyleName(MyResource.INSTANCE.getStlUIMenu().cellList());
	}

	@Override
	public void viewMenuItem(String item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeightContainer(105);
	}

	public List<String> getLista() {
		return lista;
	}
	
	
}
