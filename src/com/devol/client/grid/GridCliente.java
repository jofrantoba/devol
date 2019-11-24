package com.devol.client.grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.util.CheckCellHead;
import com.devol.client.util.CheckboxCellRow;
import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.devol.i18n.DevolConstants;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;

public class GridCliente extends DataGrid<ClienteProxy> {	
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private List<ClienteProxy> data = new ArrayList<ClienteProxy>();
	private Boolean isMultiSelection = false;
	private ListHandler<ClienteProxy> sortHandler;
	private final MultiSelectionModel<ClienteProxy> multiSelectionModel = new MultiSelectionModel<ClienteProxy>();
	private final SingleSelectionModel<ClienteProxy> selectionModel = new SingleSelectionModel<ClienteProxy>();
	private FilteredListDataProvider<ClienteProxy> dataProvider = new FilteredListDataProvider<ClienteProxy>(
			new IFilter<ClienteProxy>() {

				@Override
				public boolean isValid(ClienteProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getDni().toLowerCase() + " " + value.getNombre().toString().toLowerCase()
								+ " " + value.getApellido().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridCliente() {
		initComponents();
	}

	private void initComponents() {
		this.setWidth("100%");
		this.setHeight("90%");
		initColumns();
		this.setRowData(0, data);
		this.setRowCount(data.size(), false);
		// setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		// setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
		setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		this.sortColumns();
		this.setVisible(true);
		this.setSelectionModel(selectionModel);
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(this);
        pager.setVisible(true);
	}

	private void initColumns() {
		this.addColumn(rowNum, "#");
		this.addColumn(dni, constants.dni());
		this.addColumn(cliente, constants.clientes());
		this.setColumnWidth(rowNum, 5, Unit.EM);
		this.setColumnWidth(dni, 10, Unit.EM);
	}

	private void sortColumns() {
		sortHandler = new ListHandler<ClienteProxy>(dataProvider.getList()) {
			@Override
			public void onColumnSort(ColumnSortEvent event) {
				super.onColumnSort(event);
				redraw();
			}
		};
		this.addColumnSortHandler(sortHandler);
		dni.setSortable(true);
		cliente.setSortable(true);
		sortHandler.setComparator(dni, new Comparator<ClienteProxy>() {
			@Override
			public int compare(ClienteProxy o1, ClienteProxy o2) {
				return o1.getDni().compareTo(o2.getDni());
			}
		});
		sortHandler.setComparator(cliente, new Comparator<ClienteProxy>() {
			@Override
			public int compare(ClienteProxy o1, ClienteProxy o2) {
				String cliente1 = o1.getNombre() + " " + o1.getApellido();
				String cliente2 = o2.getNombre() + " " + o2.getApellido();
				return cliente1.compareTo(cliente2);
			}
		});
	}

	Column<ClienteProxy, Boolean> checkColumn = new Column<ClienteProxy, Boolean>(new CheckboxCellRow(true, false)) {
		@Override
		public Boolean getValue(ClienteProxy object) {
			// Get the value from the selection model.
			return multiSelectionModel.isSelected(object);
		}
	};

	public CheckCellHead checkAll = new CheckCellHead();
	Header<Boolean> headCheckAll = new Header<Boolean>(checkAll) {

		@Override
		public Boolean getValue() {
			return checkAll.isIsSelected();
		}

		@Override
		public void onBrowserEvent(Cell.Context context, Element elem, NativeEvent event) {
			if (checkAll.isIsSelected()) {
				checkAll.setIsSelected(false);
				selection(false);
			} else {
				checkAll.setIsSelected(true);
				selection(true);
			}

		}
	};

	private void selection(Boolean select) {
		if (dataProvider.getFilter() != null && !dataProvider.getFilter().isEmpty()) {
			// multiSelectionModel.clear();
			for (int i = 0; i < dataProvider.resulted.size(); i++) {
				multiSelectionModel.setSelected(dataProvider.resulted.get(i), select);
			}
		} else {
			// multiSelectionModel.clear();
			for (int i = 0; i < data.size(); i++) {
				multiSelectionModel.setSelected(data.get(i), select);
			}
		}
	}

	private Column<ClienteProxy, String> dni = new Column<ClienteProxy, String>(new TextCell()) {

		@Override
		public String getValue(ClienteProxy object) {
			return object.getDni();
		}
	};

	private Column<ClienteProxy, Number> rowNum = new Column<ClienteProxy, Number>(new NumberCell()) {

		@Override
		public Number getValue(ClienteProxy object) {			
			return dataProvider.getList().indexOf(object)+1;
		}
	};

	private Column<ClienteProxy, String> cliente = new Column<ClienteProxy, String>(new TextCell()) {

		@Override
		public String getValue(ClienteProxy object) {
			return object.getNombre() + " " + object.getApellido();
		}
	};

	public void setData(List<ClienteProxy> data) {
		if (dataProvider.getList() != null && !dataProvider.getList().isEmpty()) {
			dataProvider.getList().clear();
			this.setRowData(0, new ArrayList<ClienteProxy>());
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

	public List<ClienteProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	public void setMultiSelection() {
		this.setSelectionModel(multiSelectionModel);
		this.isMultiSelection = true;
		this.addColumn(checkColumn, headCheckAll);
		this.setColumnWidth(checkColumn, 10, Unit.PCT);
		this.redrawHeaders();
		initEditColumns();
	}

	private void initEditColumns() {
		checkColumn.setFieldUpdater(new FieldUpdater<ClienteProxy, Boolean>() {
			@Override
			public void update(int index, ClienteProxy object, Boolean value) {
				multiSelectionModel.setSelected(object, value);
			}
		});
	}

	public void setSingleSelection() {
		this.setSelectionModel(selectionModel);
		this.isMultiSelection = false;
	}

	public MultiSelectionModel<ClienteProxy> getMultiSelectionModel() {
		return multiSelectionModel;
	}

	public SingleSelectionModel<ClienteProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<ClienteProxy> getDataProvider() {
		return dataProvider;
	}

}