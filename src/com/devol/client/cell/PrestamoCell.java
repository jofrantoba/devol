package com.devol.client.cell;

import java.util.Set;

import com.devol.client.beanproxy.PrestamoProxy;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class PrestamoCell implements Cell<PrestamoProxy>{

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
			Element parent, PrestamoProxy value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,
			Element parent, PrestamoProxy value, NativeEvent event,
			ValueUpdater<PrestamoProxy> valueUpdater) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PrestamoProxy value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
		sb.appendHtmlConstant("<table width='100%' cellpadding='0' cellspacing='0' >");
		sb.appendHtmlConstant("<tr><td width='30%'>");
		sb.appendEscaped(fmt.format(value.getFecha()));		
		sb.appendHtmlConstant("</td><td width='46%'>");
		sb.appendEscaped(value.getBeanCliente().getNombre()+" "+value.getBeanCliente().getApellido());
		//sb.appendEscaped(value.getDni());
		sb.appendHtmlConstant("</td><td width='24%'>");
		sb.appendEscaped(""+value.getaDevolver());
		sb.appendHtmlConstant("</td></tr>");
		sb.appendHtmlConstant("</table>");
	}

	@Override
	public boolean resetFocus(com.google.gwt.cell.client.Cell.Context context,
			Element parent, PrestamoProxy value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValue(com.google.gwt.cell.client.Cell.Context context,
			Element parent, PrestamoProxy value) {
		// TODO Auto-generated method stub
		
	}

}
