package com.devol.client.cell;

import java.util.Set;

import com.devol.client.beanproxy.ClienteProxy;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ClienteCell implements Cell<ClienteProxy> {

	@Override
	public boolean dependsOnSelection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getConsumedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handlesSelection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEditing(com.google.gwt.cell.client.Cell.Context context,
			Element parent, ClienteProxy value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,
			Element parent, ClienteProxy value, NativeEvent event,
			ValueUpdater<ClienteProxy> valueUpdater) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			ClienteProxy value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		sb.appendHtmlConstant("<table width='100%' cellpadding='0' cellspacing='0' >");
		sb.appendHtmlConstant("<tr><td width='40%'>");
		sb.appendEscaped(value.getDni());
		sb.appendHtmlConstant("</td><td width='60%'>");
		sb.appendEscaped(value.getNombre() + " " + value.getApellido());
		sb.appendHtmlConstant("</td></tr>");
		sb.appendHtmlConstant("</table>");
	}

	@Override
	public boolean resetFocus(com.google.gwt.cell.client.Cell.Context context,
			Element parent, ClienteProxy value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValue(com.google.gwt.cell.client.Cell.Context context,
			Element parent, ClienteProxy value) {
		// TODO Auto-generated method stub

	}

}
