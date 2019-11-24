package com.devol.client.grid;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridAmortizacion extends DataGrid<AmortizacionProxy> {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private List<AmortizacionProxy> data = new ArrayList<AmortizacionProxy>();
	private ListHandler<AmortizacionProxy> sortHandler;
	private final SingleSelectionModel<AmortizacionProxy> selectionModel = new SingleSelectionModel<AmortizacionProxy>();
	private DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
	private FilteredListDataProvider<AmortizacionProxy> dataProvider = new FilteredListDataProvider<AmortizacionProxy>(
			new IFilter<AmortizacionProxy>() {

				@Override
				public boolean isValid(AmortizacionProxy value, String filter) {
					// TODO Auto-generated method stub
					if (filter == null || value == null)
						return true;
					String values = " " + fmt.format(value.getFecha()) + " "
							+ value.getMonto();
					return values.contains(filter.toLowerCase());
				}
			});

	public GridAmortizacion() {				
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
		this.sortColumns();
		this.setVisible(true);
        this.setSelectionModel(selectionModel);
	}
	
	private void initColumns() {
		fecha.setSortable(true);
		monto.setSortable(true);
		cobrador.setSortable(true);
        this.addColumn(fecha, constants.fecha());
        this.addColumn(monto, constants.monto()); 
        this.addColumn(cobrador, constants.cobrador());
    }
	
	private void sortColumns(){
    	sortHandler=new ListHandler<AmortizacionProxy>(dataProvider.getList()){
            	@Override
                public void onColumnSort(ColumnSortEvent event) {
                    super.onColumnSort(event);                
                    redraw();
                }
            };        
            this.addColumnSortHandler(sortHandler);
            fecha.setSortable(true);
            monto.setSortable(true);
            cobrador.setSortable(true);
    	sortHandler.setComparator(fecha, new Comparator<AmortizacionProxy>() {
    	      @Override
    	      public int compare(AmortizacionProxy o1, AmortizacionProxy o2) {    	    	
    	        return o1.getFecha().compareTo(o2.getFecha());
    	      }
    	    });
    	
    	sortHandler.setComparator(monto, new Comparator<AmortizacionProxy>() {
  	      @Override
  	      public int compare(AmortizacionProxy o1, AmortizacionProxy o2) {    	    	
  	        return o1.getMonto().compareTo(o2.getMonto());
  	      }
  	    });
    	sortHandler.setComparator(cobrador, new Comparator<AmortizacionProxy>() {
  	      @Override
  	      public int compare(AmortizacionProxy o1, AmortizacionProxy o2) {
  	    	String nombresCobrador1=o1.getNombresCobrador()!=null?o1.getNombresCobrador():"";
			String apellidoCobrador1=o1.getApellidosCobrador()!=null?o1.getApellidosCobrador():"";
			String cobrador1=  nombresCobrador1+ " " + apellidoCobrador1;
			String nombresCobrador2=o2.getNombresCobrador()!=null?o2.getNombresCobrador():"";
			String apellidoCobrador2=o2.getApellidosCobrador()!=null?o2.getApellidosCobrador():"";
			String cobrador2=  nombresCobrador2+ " " + apellidoCobrador2;
        return cobrador1.compareTo(cobrador2);
  	      }
  	    });
    }
	
	private Column<AmortizacionProxy, String> cobrador = new Column<AmortizacionProxy, String>(new TextCell()) {

		@Override
		public String getValue(AmortizacionProxy object) {
			String nombresCobrador=object.getNombresCobrador()!=null?object.getNombresCobrador():"";
			String apellidoCobrador=object.getApellidosCobrador()!=null?object.getApellidosCobrador():"";
			return  nombresCobrador+ " " + apellidoCobrador;
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
    		sortHandler.setList(dataProvider.getList());
    	}    	    	
        this.data = data;        
        this.setRowData(0, data);
        this.setRowCount(data.size(), false); 
        setPageSize(data.size());
        dataProvider.setList(data);   
        dataProvider.refresh();
        dataProvider.flush();
        sortHandler.setList(dataProvider.getList());
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
