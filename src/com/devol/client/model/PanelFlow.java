package com.devol.client.model;

import com.devol.client.resource.MyResource;
import com.google.gwt.user.client.ui.FlowPanel;

public class PanelFlow extends FlowPanel{
	
	public PanelFlow(){
		initStyle();
	}
	
	private void initStyle(){
		MyResource.INSTANCE.getStlPanelFlow().ensureInjected();
		addStyleName(MyResource.INSTANCE.getStlPanelFlow().panelFlow());
	}
}
