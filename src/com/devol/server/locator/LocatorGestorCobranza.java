package com.devol.server.locator;

import com.devol.server.model.bean.GestorCobranza;
import com.google.web.bindery.requestfactory.shared.Locator;

public class LocatorGestorCobranza extends Locator<GestorCobranza, String>{

	@Override
	public GestorCobranza create(Class<? extends GestorCobranza> clazz) {
		// TODO Auto-generated method stub
		return new GestorCobranza();
	}

	@Override
	public GestorCobranza find(Class<? extends GestorCobranza> clazz, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<GestorCobranza> getDomainType() {
		// TODO Auto-generated method stub
		return GestorCobranza.class;
	}

	@Override
	public String getId(GestorCobranza domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdGestorCobranza();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(GestorCobranza domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}
	
	@Override
	public boolean isLive(GestorCobranza domainObject) {
	    // Can't us Class.asSubclass() in web-mode code
	    return true;
	  }

}
