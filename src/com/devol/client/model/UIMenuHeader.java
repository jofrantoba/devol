package com.devol.client.model;

import com.devol.client.resource.MyResource;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;

public class UIMenuHeader extends HeaderMenu {

	public UIMenuHeader() {
		init();
		style();
	}

	private void init() {
		// TODO Auto-generated method stub		
	}

	private void style() {
		MyResource.INSTANCE.getStlUIMenu().ensureInjected();
		// TODO Auto-generated method stub
		this.addStyleName(MyResource.INSTANCE.getStlUIMenu().headerTittleUI());
	}
}
