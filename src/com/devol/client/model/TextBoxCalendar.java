package com.devol.client.model;

import java.util.Date;

import com.devol.client.resource.MyResource;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TextBoxCalendar extends FlowPanel implements ClickHandler,
		ResizeHandler {

	private FlexTable flexTable;
	protected TextBox txtFecha;
	private PushButton btnCalendar;
	protected PopupPanel dialogBox;
	private UICalendar calendar;

	public TextBoxCalendar() {
		init();
		initWidgetListener();
		style();
	}

	private void init() {
		Window.addResizeHandler(this);
		flexTable = new FlexTable();
		add(flexTable);

		txtFecha = new TextBox();
		txtFecha.setReadOnly(true);
		flexTable.setWidget(0, 0, txtFecha);

		btnCalendar = new PushButton(new Image(
				MyResource.INSTANCE.getImgCalendar32()));
		flexTable.setWidget(0, 1, btnCalendar);
		Date fecha = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
		String strFecha = fmt.format(fecha);
		txtFecha.setText(strFecha);
	}

	private void initWidgetListener() {
		btnCalendar.addClickHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlModel().ensureInjected();

		flexTable.setCellPadding(0);
		flexTable.setCellSpacing(0);
		flexTable.setWidth("100%");

		txtFecha.setWidth("100%");
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		cellFormatter.setWidth(0, 1, "40");

		flexTable.addStyleName(MyResource.INSTANCE.getStlModel()
				.flexTableTextBoxCalendar());
		txtFecha.addStyleName(MyResource.INSTANCE.getStlModel()
				.textBoxFechaTextBoxCalendar());
		btnCalendar.setHeight("25px");
		calendar= new UICalendar(this);	
	}

	

	public void showCalendar() {
		dialogBox = new PopupPanel();
		dialogBox.setModal(true);		
		dialogBox.setWidget(calendar);

		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);

		resizeDialog();
		dialogBox.center();
		dialogBox.show();

		dialogBox.addStyleName(MyResource.INSTANCE.getStlModel()
				.dialogCalendarTextBoxCalendar());
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		resizeDialog();
	}
	
	private void resizeDialog(){
		int width = Window.getClientWidth();
		if (dialogBox == null) {
			return;
		}
		if (dialogBox.isShowing()) {
			if (width < 600) {
				dialogBox.setWidth((width - 50) + "px");
			} else {
				dialogBox.setWidth("500px");
			}
			dialogBox.center();
		}
	}
	
	public String getText(){
		return txtFecha.getText();
	}	
	
	public Date getDate(){
		Date fecha=new Date();
		DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
		fecha=format.parse(txtFecha.getText());
		return fecha;
	}

	public TextBox getTxtFecha() {
		return txtFecha;
	}

	public UICalendar getCalendar() {
		return calendar;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnCalendar)) {
			showCalendar();
		}
	}
	
	

}
