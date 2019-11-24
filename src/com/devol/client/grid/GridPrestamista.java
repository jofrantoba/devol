package com.devol.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.GestorCobranzaProxy;
import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.devol.i18n.DevolConstants;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridPrestamista extends DataGrid<GestorCobranzaProxy> {
	private DevolConstants constants = GWT.create(DevolConstants.class);
    private List<GestorCobranzaProxy> data = new ArrayList<GestorCobranzaProxy>();
    private final SingleSelectionModel<GestorCobranzaProxy> selectionModel = new SingleSelectionModel<GestorCobranzaProxy>();
    private FilteredListDataProvider<GestorCobranzaProxy> dataProvider = new FilteredListDataProvider<GestorCobranzaProxy>(new IFilter<GestorCobranzaProxy>() {

        @Override
        public boolean isValid(GestorCobranzaProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getBeanUsuarioOwner().getCorreo().toLowerCase()+" "+value.getBeanUsuarioOwner().getNombres().toString().toLowerCase()+" "+value.getBeanUsuarioOwner().getApellidos().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridPrestamista() {
        initComponents();        
    }

    private void initComponents() {
        this.setWidth("100%");
        this.setHeight("90%");
        initColumns();        
        this.setRowData(0, data);
        this.setRowCount(data.size(), false);
        //setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		//setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
		setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.addDataDisplay(this);
        this.setVisible(true);
        this.setSelectionModel(selectionModel);
    }   

    private void initColumns() {
    	correo.setSortable(true);
    	prestamista.setSortable(true);
        this.addColumn(correo, constants.correo());
        this.addColumn(prestamista, constants.prestamista());               
    }       

    private Column<GestorCobranzaProxy, String> correo
            = new Column<GestorCobranzaProxy, String>(new TextCell()) {

                @Override
                public String getValue(GestorCobranzaProxy object) {
                    return object.getBeanUsuarioOwner().getCorreo();
                }
            };
    
    private Column<GestorCobranzaProxy, String> prestamista
            = new Column<GestorCobranzaProxy, String>(new TextCell()) {

                @Override
                public String getValue(GestorCobranzaProxy object) {
                    return object.getBeanUsuarioOwner().getNombres()+" "+object.getBeanUsuarioOwner().getApellidos();
                }
            };
        
    public void setData(List<GestorCobranzaProxy> data) {    	
    	if(dataProvider.getList()!=null && !dataProvider.getList().isEmpty()){
    		dataProvider.getList().clear();
    		this.setRowData(0, new ArrayList<GestorCobranzaProxy>());
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

    public List<GestorCobranzaProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public SingleSelectionModel<GestorCobranzaProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<GestorCobranzaProxy> getDataProvider() {
        return dataProvider;
    }
    
    
}