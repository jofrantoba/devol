package com.devol.client.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devol.client.beanproxy.AmortizacionProxy;
import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.devol.i18n.DevolConstants;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridRecaudado extends DataGrid<AmortizacionProxy> {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private List<AmortizacionProxy> data = new ArrayList<AmortizacionProxy>();
	private final SingleSelectionModel<AmortizacionProxy> selectionModel = new SingleSelectionModel<AmortizacionProxy>();
	private DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
	private FilteredListDataProvider<AmortizacionProxy> dataProvider = new FilteredListDataProvider<AmortizacionProxy>(
			new IFilter<AmortizacionProxy>() {

				@Override
				public boolean isValid(AmortizacionProxy value, String filter) {
					// TODO Auto-generated method stub
					if (filter == null || value == null)
						return true;
					String values = value.getBeanPrestamo().getNombre().toLowerCase() + " "
							+ value.getBeanPrestamo().getApellido().toLowerCase()+" "+value.getBeanPrestamo().getDni()+" "+fmt.format(value.getFecha())+" "+value.getMonto();
					return values.contains(filter.toLowerCase());
				}
			});

	public GridRecaudado() {			
		initComponents();
	}
	
	private void initComponents(){			
		this.setWidth("100%");
        this.setHeight("90%");
        initColumns();
		setRowData(0, data);
		setRowCount(data.size(), false);
		setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);		
		this.setVisible(true);
        this.setSelectionModel(selectionModel);
	}

	private void initColumns() {        
		this.addColumn(fecha, constants.fecha());
        this.addColumn(cliente, constants.clientes());
        this.addColumn(monto, constants.monto());
    }
    
    private Column<AmortizacionProxy, String> cliente
            = new Column<AmortizacionProxy, String>(new TextCell()) {

                @Override
                public String getValue(AmortizacionProxy object) {
                    return object.getBeanPrestamo().getNombre()+" "+object.getBeanPrestamo().getApellido();
                }
            };
            
    private Column<AmortizacionProxy, Date> fecha
            = new Column<AmortizacionProxy, Date>(new DateCell(fmt)) {

                @Override
				public Date getValue(AmortizacionProxy object) {
					return object.getFecha();
                }
            };
            
    private Column<AmortizacionProxy, Number> monto
            = new Column<AmortizacionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(AmortizacionProxy object) {
                    return object.getMonto();
                }
            };

	public void setData(List<AmortizacionProxy> data) {		
		if(dataProvider.getList()!=null && !dataProvider.getList().isEmpty()){
    		dataProvider.getList().clear();
    		this.setRowData(0, new ArrayList<AmortizacionProxy>());
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

	public List<AmortizacionProxy> getData() {
		return dataProvider.getList();
		//return data;
	}

	public SingleSelectionModel<AmortizacionProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<AmortizacionProxy> getDataProvider() {
		return dataProvider;
	}

}
