package com.devol.client.model;

import com.devol.client.resource.MyResource;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContentForm extends FlowPanel {

	private FlexTable flexTable;
	private int row = 0;

	public ContentForm() {
		init();
		style();
	}

	private void init() {
		flexTable = new FlexTable();
		flexTable.setCellPadding(0);
		flexTable.setCellSpacing(0);
		add(flexTable);
	}

	public void addWidget(String text, Widget widget) {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		flexTable.setText(row, 0, text);
		flexTable.setWidget(row, 1, widget);
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		cellFormatter.setWidth(row, 0, "30%");
		cellFormatter.setWidth(row, 1, "70%");
		cellFormatter.getElement(row, 0).getStyle().setFontWeight(Style.FontWeight.BOLD);
		cellFormatter.getElement(row, 0).getStyle().setFontSize(1, Unit.EM);
		cellFormatter.getElement(row, 0).getStyle().setColor("#000");
		cellFormatter.getElement(row, 1).getStyle().setPadding(10, Unit.PX);
        cellFormatter.getElement(row, 1).getStyle().setBackgroundColor("#fff");
		row++;
		widget.addStyleName(MyResource.INSTANCE.getStlModel()
				.widgetContentForm());
	}

	private void style() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		addStyleName(MyResource.INSTANCE.getStlModel().mainContentForm());
		flexTable.addStyleName(MyResource.INSTANCE.getStlModel()
				.flexTableContentForm());
	}
}
