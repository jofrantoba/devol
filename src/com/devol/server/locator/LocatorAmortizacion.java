package com.devol.server.locator;

import com.devol.server.model.bean.Amortizacion;
import com.google.web.bindery.requestfactory.shared.Locator;

public class LocatorAmortizacion extends Locator<Amortizacion, String> {

	@Override
	public Amortizacion create(Class<? extends Amortizacion> clazz) {
		// TODO Auto-generated method stub
		return new Amortizacion();
	}

	@Override
	public Amortizacion find(Class<? extends Amortizacion> clazz, String id) {
		// TODO Auto-generated method stub
		/*PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			Amortizacion bean = (Amortizacion) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			//ex.printStackTrace();
			return null;
		}*/
		return null;
	}

	@Override
	public Class<Amortizacion> getDomainType() {
		// TODO Auto-generated method stub
		return Amortizacion.class;
	}

	@Override
	public String getId(Amortizacion domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdAmortizacion();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Amortizacion domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}
	
	@Override
	public boolean isLive(Amortizacion domainObject) {
	    // Can't us Class.asSubclass() in web-mode code
	    return true;
	  }

}
