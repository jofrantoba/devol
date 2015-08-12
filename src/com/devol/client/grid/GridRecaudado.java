package com.devol.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.AmortizacionProxy;
import com.devol.client.cell.RecaudadoCell;
import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridRecaudado extends CellList<AmortizacionProxy> {

	//private List<AmortizacionProxy> data = new ArrayList<AmortizacionProxy>();
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
		super(new RecaudadoCell());
		List<AmortizacionProxy> data = new ArrayList<AmortizacionProxy>();
		this.setRowCount(data.size(), true);
		setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
		setRowData(0, data);
		super.setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);			
		setSelectionModel(selectionModel);
	}

	public void setData(List<AmortizacionProxy> data) {		
		dataProvider.setFilter(null);
		//this.data = data;
		this.setRowCount(data.size(), true);
		super.setRowData(0, data);
		super.setPageSize(data.size());
		super.setSelectionModel(selectionModel);		
		dataProvider.setList(data);				
		dataProvider.refresh();		
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
