package com.devol.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.cell.ClienteCell;
import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridCliente extends CellList<ClienteProxy> {

	//private List<ClienteProxy> data = new ArrayList<ClienteProxy>();
	private final SingleSelectionModel<ClienteProxy> selectionModel = new SingleSelectionModel<ClienteProxy>();
	private FilteredListDataProvider<ClienteProxy> dataProvider = new FilteredListDataProvider<ClienteProxy>(
			new IFilter<ClienteProxy>() {

				@Override
				public boolean isValid(ClienteProxy value, String filter) {
					// TODO Auto-generated method stub
					if (filter == null || value == null)
						return true;
					String values = value.getDni() + " "
							+ value.getNombre().toLowerCase() + " "
							+ value.getApellido().toLowerCase();
					return values.contains(filter.toLowerCase());
				}
			});

	public GridCliente() {		
		super(new ClienteCell());
		List<ClienteProxy> data = new ArrayList<ClienteProxy>();		
		this.setRowCount(data.size(), true);
		setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
		setRowData(0, data);
		super.setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		setSelectionModel(selectionModel);	
		this.redraw();
	}

	public void setData(List<ClienteProxy> data) {
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

	public List<ClienteProxy> getData() {
		return dataProvider.getList();
		//return data;
	}

	public SingleSelectionModel<ClienteProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<ClienteProxy> getDataProvider() {
		return dataProvider;
	}

}
