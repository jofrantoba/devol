package com.devol.client.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;

public class MenuCell extends AbstractCell<String> {
	private DevolConstants constants = GWT.create(DevolConstants.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,			
			String value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		String imageHtml = "";

		if (value.equalsIgnoreCase(constants.clientes())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgClientes32()).getHTML();
		} else if (value.equalsIgnoreCase(constants.prestamos())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgPago32()).getHTML();
		} else if (value.equalsIgnoreCase(constants.historial())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgCheckPago32()).getHTML();
		} else if (value.equalsIgnoreCase(constants.reportes())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgRepote32()).getHTML();
		}/*
		 * else if (model.equalsIgnoreCase("Mi Cuenta")) { imageHtml =
		 * AbstractImagePrototype.create(
		 * MyResource.INSTANCE.getImgUserSettings32()).getHTML(); }
		 */else if (value.equalsIgnoreCase(constants.salir())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgOff32()).getHTML();
		}
		sb.appendHtmlConstant("<div style='display:block; border-bottom: solid 1px #000;background-color: #FBFF88; vertical-align: middle; font-weight: bold; color: #000;'>");
		sb.appendHtmlConstant("<div style='width: 45px; display:inline-block; height: 45px; margin=5px;'>");
		sb.appendHtmlConstant(imageHtml);
		sb.appendHtmlConstant("</div>");
		sb.appendHtmlConstant("<div style=' height: 38px; display:inline-block; line-height:35px;'>");
		sb.appendEscaped(value);
		sb.appendHtmlConstant("</div>");
		sb.appendHtmlConstant("</div>");
	}

}
