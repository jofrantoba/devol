package com.devol.client.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.devol.i18n.DevolConstants;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridPrestamo extends DataGrid<PrestamoProxy> {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private List<PrestamoProxy> data = new ArrayList<PrestamoProxy>();
	private final SingleSelectionModel<PrestamoProxy> selectionModel = new SingleSelectionModel<PrestamoProxy>();
	private DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
	private FilteredListDataProvider<PrestamoProxy> dataProvider = new FilteredListDataProvider<PrestamoProxy>(
			new IFilter<PrestamoProxy>() {

				@Override
				public boolean isValid(PrestamoProxy value, String filter) {
					// TODO Auto-generated method stub
					if (filter == null || value == null)
						return true;
					String values = value.getBeanCliente().getNombre().toLowerCase() + " "
							+ value.getBeanCliente().getApellido().toLowerCase()+" "+value.getBeanCliente().getDni()+" "+fmt.format(value.getFecha())+" "+value.getMonto();
					return values.contains(filter.toLowerCase());
				}
			});
	
	public GridPrestamo() {
		initComponents();
	}
	
	private void initComponents(){		
		this.setWidth("100%");
        this.setHeight("90%");
        initColumns();
		setRowData(0, data);		
		this.setRowCount(data.size(), true);
		//setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		//setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
		setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		setVisible(true);
        setSelectionModel(selectionModel);
	}
	
	private void initColumns() {        
		this.addColumn(fecha, constants.fecha());
        this.addColumn(cliente, constants.clientes());
        this.addColumn(monto, constants.monto());
    }
    
    private Column<PrestamoProxy, String> cliente
            = new Column<PrestamoProxy, String>(new TextCell()) {

                @Override
                public String getValue(PrestamoProxy object) {
                    return object.getBeanCliente().getNombre()+" "+object.getBeanCliente().getApellido();
                }
            };
            
    private Column<PrestamoProxy, Date> fecha
            = new Column<PrestamoProxy, Date>(new DateCell(fmt)) {

                @Override
				public Date getValue(PrestamoProxy object) {
					return object.getFecha();
                }
            };
            
    private Column<PrestamoProxy, Number> monto
            = new Column<PrestamoProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(PrestamoProxy object) {
                    return object.getMonto();
                }
            };
	
	public void setData(List<PrestamoProxy> data) {
		if(dataProvider.getList()!=null && !dataProvider.getList().isEmpty()){
    		dataProvider.getList().clear();
    		this.setRowData(0, new ArrayList<PrestamoProxy>());
    		this.setRowCount(0);
    		dataProvider.refresh();
    		dataProvider.flush();
    	}    	    	
        this.data = data;        
        this.setRowData(0, data);
        this.setRowCount(data.size(), false); 
        setPageSize(data.size());
        dataProvider.setList(data);   
        dataProvider.refresh();
        dataProvider.flush();
        this.flush();
        this.redraw();        
	}
	
	

	public List<PrestamoProxy> getData() {
		return dataProvider.getList();
		//return data;
	}

	public SingleSelectionModel<PrestamoProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<PrestamoProxy> getDataProvider() {
		return dataProvider;
	}

}
