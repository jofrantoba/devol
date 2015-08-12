package com.devol.client.model;

import java.util.Date;

import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UICalendar extends FlowPanel implements TouchEndHandler,ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private HorizontalPanel container;
	private VerticalPanel pnlDia;
	private PushButton btnNextDia;
	private Label lblDia;
	private PushButton btnBackDia;
	private VerticalPanel pnlMes;
	private PushButton btnNextMes;
	private Label lblMes;
	private PushButton btnBackMes;
	private VerticalPanel pnlAnnio;
	private PushButton btnNextAnio;
	private Label lblAnio;
	private PushButton btnBackAnio;
	private HorizontalPanel pnlFoot;
	private Button btnAceptar;
	private Button btnCancelar;
	private TextBoxCalendar textBoxCalendar;

	public UICalendar(TextBoxCalendar textBoxCalendar) {
		this.textBoxCalendar = textBoxCalendar;
		init();
		initWidgetListener();
		style();		
	}

	private void init() {
		container = new HorizontalPanel();
		add(container);

		pnlDia = new VerticalPanel();
		container.add(pnlDia);

		btnNextDia = new PushButton(new Image(MyResource.INSTANCE.getImgUp32()));
		pnlDia.add(btnNextDia);
		lblDia = new Label("23");
		pnlDia.add(lblDia);
		btnBackDia = new PushButton(new Image(MyResource.INSTANCE.getImgDown32()));
		pnlDia.add(btnBackDia);

		pnlMes = new VerticalPanel();
		container.add(pnlMes);

		btnNextMes = new PushButton(new Image(MyResource.INSTANCE.getImgUp32()));
		pnlMes.add(btnNextMes);
		lblMes = new Label("02");
		pnlMes.add(lblMes);
		btnBackMes = new PushButton(new Image(MyResource.INSTANCE.getImgDown32()));
		pnlMes.add(btnBackMes);

		pnlAnnio = new VerticalPanel();
		container.add(pnlAnnio);

		btnNextAnio = new PushButton(new Image(MyResource.INSTANCE.getImgUp32()));
		pnlAnnio.add(btnNextAnio);
		lblAnio = new Label("2014");
		pnlAnnio.add(lblAnio);
		btnBackAnio = new PushButton(new Image(MyResource.INSTANCE.getImgDown32()));
		pnlAnnio.add(btnBackAnio);

		pnlFoot = new HorizontalPanel();
		add(pnlFoot);

		btnAceptar = new Button(constants.aceptar());
		pnlFoot.add(btnAceptar);

		btnCancelar = new Button(constants.cancelar());
		pnlFoot.add(btnCancelar);

		initFecha();

	}

	private void initFecha() {
		Date fecha = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
		String strFecha = fmt.format(fecha);
		if (textBoxCalendar.txtFecha.getText().length() > 0) {
			strFecha = textBoxCalendar.txtFecha.getText();
		}

		setFecha(Integer.parseInt(strFecha.substring(0, 2)),
				Integer.parseInt(strFecha.substring(3, 5)),
				Integer.parseInt(strFecha.substring(6, 10)));

	}

	private void setFecha(int dia, int mes, int anio) {
		String strDia = "" + dia;
		String strMes = "" + mes;
		if (dia < 10) {
			strDia = "0" + dia;
		}
		if (mes < 10) {
			strMes = "0" + mes;
		}

		lblDia.setText(strDia);
		lblMes.setText(strMes);
		lblAnio.setText("" + anio);
	}

	private void initWidgetListener() {
		btnNextDia.addClickHandler(this);
		btnBackDia.addClickHandler(this);

		btnNextMes.addClickHandler(this);
		btnBackMes.addClickHandler(this);

		btnNextAnio.addClickHandler(this);
		btnBackAnio.addClickHandler(this);

		btnAceptar.addTouchEndHandler(this);
		btnCancelar.addTouchEndHandler(this);
		
		btnAceptar.addClickHandler(this);
		btnCancelar.addClickHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		// setWidth("240px");
		container.setWidth("100%");
		pnlFoot.setWidth("100%");

		this.addStyleName(MyResource.INSTANCE.getStlModel().mainUICalendar());
		container.addStyleName(MyResource.INSTANCE.getStlModel()
				.containerUICalendar());
		btnNextDia.addStyleName(MyResource.INSTANCE.getStlModel()
				.buttonUICalendar());
		btnNextDia.addStyleName(MyResource.INSTANCE.getStlModel()
				.buttonUICalendar());
		btnBackDia.addStyleName(MyResource.INSTANCE.getStlModel()
				.buttonUICalendar());
		btnNextMes.addStyleName(MyResource.INSTANCE.getStlModel()
				.buttonUICalendar());
		btnBackMes.addStyleName(MyResource.INSTANCE.getStlModel()
				.buttonUICalendar());
		btnNextAnio.addStyleName(MyResource.INSTANCE.getStlModel()
				.buttonUICalendar());
		btnBackAnio.addStyleName(MyResource.INSTANCE.getStlModel()
				.buttonUICalendar());
		btnAceptar.getElement().getStyle().setFontSize(1.3, Unit.EM);
		btnAceptar.setWidth("100%");
		btnCancelar.getElement().getStyle().setFontSize(1.3, Unit.EM);
		btnCancelar.setWidth("100%");
	}


	private void nextDia() {
		int anio = Integer.parseInt(lblAnio.getText());
		int mes = Integer.parseInt(lblMes.getText());
		int dia = Integer.parseInt(lblDia.getText());
		dia++;
		if (dia > evaluarMes(mes, anio)) {
			mes++;
			if (mes == 13) {
				mes = 1;
				anio++;
			}
			dia = 1;
		}
		setFecha(dia, mes, anio);
	}

	private void backDia() {
		int anio = Integer.parseInt(lblAnio.getText());
		int mes = Integer.parseInt(lblMes.getText());
		int dia = Integer.parseInt(lblDia.getText());
		dia--;
		int diasOfMes = evaluarMes(mes, anio);
		if (!(dia <= diasOfMes && dia > 0)) {
			mes--;
			if (mes == 0) {
				mes = 12;
				anio--;
			}
			diasOfMes = evaluarMes(mes, anio);
			dia = diasOfMes;
		}
		setFecha(dia, mes, anio);
	}

	private void nextMes() {
		int anio = Integer.parseInt(lblAnio.getText());
		int mes = Integer.parseInt(lblMes.getText());
		int dia = Integer.parseInt(lblDia.getText());
		int diasOfMes = evaluarMes(mes, anio);
		mes++;
		if (mes == 13) {
			mes = 1;
			anio++;
		}
		if (dia == diasOfMes || dia > evaluarMes(mes, anio)) {
			diasOfMes = evaluarMes(mes, anio);
			dia = diasOfMes;
		}

		setFecha(dia, mes, anio);
	}

	private void backMes() {
		int anio = Integer.parseInt(lblAnio.getText());
		int mes = Integer.parseInt(lblMes.getText());
		int dia = Integer.parseInt(lblDia.getText());
		int diasOfMes = evaluarMes(mes, anio);
		mes--;
		if (mes == 0) {
			mes = 12;
			anio--;
		}
		if (dia == diasOfMes || dia > evaluarMes(mes, anio)) {
			diasOfMes = evaluarMes(mes, anio);
			dia = diasOfMes;
		}

		setFecha(dia, mes, anio);
	}

	private void nextAnio() {
		int anio = Integer.parseInt(lblAnio.getText());
		int mes = Integer.parseInt(lblMes.getText());
		int dia = Integer.parseInt(lblDia.getText());
		int diasOfMes = evaluarMes(mes, anio);
		anio++;

		if (dia == diasOfMes) {
			diasOfMes = evaluarMes(mes, anio);
			dia = diasOfMes;
		}

		setFecha(dia, mes, anio);
	}

	private void backAnio() {
		int anio = Integer.parseInt(lblAnio.getText());
		int mes = Integer.parseInt(lblMes.getText());
		int dia = Integer.parseInt(lblDia.getText());
		int diasOfMes = evaluarMes(mes, anio);
		anio--;

		if (dia == diasOfMes) {
			diasOfMes = evaluarMes(mes, anio);
			dia = diasOfMes;
		}

		setFecha(dia, mes, anio);
	}

	private int evaluarMes(int mes, int anio) {
		int xmes = 0; // Cantidad de d�as del mes

		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8
				|| mes == 10 || mes == 12) {
			xmes = 31;
		} else {
			if (mes == 2) {
				if (anio % 4 == 0) {
					xmes = 29;
					if (anio % 100 == 0 && anio % 400 != 0) {
						xmes = 28;
					}
				} else {
					xmes = 28;
				}
			} else {
				xmes = 30;
			}
		}
		return xmes;
	}

	public Button getBtnAceptar() {
		return btnAceptar;
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnAceptar)) {
			textBoxCalendar.txtFecha.setText(lblDia.getText() + "/"
					+ lblMes.getText() + "/" + lblAnio.getText());
			textBoxCalendar.dialogBox.hide();
			textBoxCalendar.dialogBox = null;
		} else if (event.getSource().equals(btnCancelar)) {
			textBoxCalendar.dialogBox.hide();
			textBoxCalendar.dialogBox = null;
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnNextDia)) {
			nextDia();
		} else if (event.getSource().equals(btnBackDia)) {
			backDia();
		} else if (event.getSource().equals(btnNextMes)) {
			nextMes();
		} else if (event.getSource().equals(btnBackMes)) {
			backMes();
		} else if (event.getSource().equals(btnNextAnio)) {
			nextAnio();
		} else if (event.getSource().equals(btnBackAnio)) {
			backAnio();
		} else if (event.getSource().equals(btnAceptar)) {
			textBoxCalendar.txtFecha.setText(lblDia.getText() + "/"
					+ lblMes.getText() + "/" + lblAnio.getText());
			textBoxCalendar.dialogBox.hide();
			textBoxCalendar.dialogBox = null;
		} else if (event.getSource().equals(btnCancelar)) {
			textBoxCalendar.dialogBox.hide();
			textBoxCalendar.dialogBox = null;
		}
	}
	
	

}
