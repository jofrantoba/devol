package com.devol.server.locator;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.Prestamo;
import com.devol.server.model.dao.PMF;
import com.devol.server.model.dao.Querys;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;

public class LocatorPrestamo extends Locator<Prestamo, String> {

	@Override
	public Prestamo create(Class<? extends Prestamo> clazz) {
		// TODO Auto-generated method stub
		return new Prestamo();
	}

	@Override
	public Prestamo find(Class<? extends Prestamo> clazz, String id) {
		// TODO Auto-generated method stub
		/*PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			Prestamo bean = (Prestamo) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			//ex.printStackTrace();
			return null;
		}*/
		return null;
	}

	@Override
	public Class<Prestamo> getDomainType() {
		// TODO Auto-generated method stub
		return Prestamo.class;
	}

	@Override
	public String getId(Prestamo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdPrestamo();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Prestamo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}

}
