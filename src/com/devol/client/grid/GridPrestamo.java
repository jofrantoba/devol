package com.devol.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.cell.PrestamoCell;
import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridPrestamo extends CellList<PrestamoProxy> {

	//private List<PrestamoProxy> data = new ArrayList<PrestamoProxy>();
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
							+ value.getBeanCliente().getApellido().toLowerCase()+" "+value.getDni()+" "+fmt.format(value.getFecha())+" "+value.getaDevolver();
					return values.contains(filter.toLowerCase());
				}
			});
	
	public GridPrestamo() {
		super(new PrestamoCell());
		List<PrestamoProxy> data = new ArrayList<PrestamoProxy>();
		this.setRowCount(data.size(), true);
		setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
		setRowData(0, data);
		super.setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		setSelectionModel(selectionModel); 
	}
	
	public void setData(List<PrestamoProxy> data) {
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
