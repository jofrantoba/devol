package com.devol.server.locator;

import com.devol.server.model.bean.GestorCliente;
import com.google.web.bindery.requestfactory.shared.Locator;

public class LocatorGestorCliente extends Locator<GestorCliente, String>{

	@Override
	public GestorCliente create(Class<? extends GestorCliente> clazz) {
		// TODO Auto-generated method stub
		return new GestorCliente();
	}

	@Override
	public GestorCliente find(Class<? extends GestorCliente> clazz, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<GestorCliente> getDomainType() {
		// TODO Auto-generated method stub
		return GestorCliente.class;
	}

	@Override
	public String getId(GestorCliente domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdGestorCliente();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(GestorCliente domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}
	
	@Override
	public boolean isLive(GestorCliente domainObject) {
	    // Can't us Class.asSubclass() in web-mode code
	    return true;
	  }

}
