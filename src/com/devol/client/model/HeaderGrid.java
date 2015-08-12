package com.devol.client.model;

import com.devol.client.resource.MyResource;
import com.google.gwt.user.client.ui.FlowPanel;

public class HeaderGrid extends FlowPanel {

	public HeaderGrid() {
		init();
		style();
	}

	private void init() {
		setWidth("100%");
		setHeight("36px");
	}

	private void style() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		addStyleName(MyResource.INSTANCE.getStlModel().mainHeaderGrid());
	}
}
