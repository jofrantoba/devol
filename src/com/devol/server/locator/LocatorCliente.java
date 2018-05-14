package com.devol.server.locator;

import com.devol.server.model.bean.Cliente;
import com.google.web.bindery.requestfactory.shared.Locator;

public class LocatorCliente extends Locator<Cliente, String> {

	@Override
	public Cliente create(Class<? extends Cliente> clazz) {
		// TODO Auto-generated method stub
		return new Cliente();
	}

	@Override
	public Cliente find(Class<? extends Cliente> clazz, String id) {
		// TODO Auto-generated method stub
		/*PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			Cliente bean = (Cliente) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			//ex.printStackTrace();
			return null;
		}*/
		if(id!=null && !id.isEmpty()){
			Cliente bean=new Cliente();
			bean.setIdCliente(id);
			return bean;
		}else{
			return null;
		}		
	}

	@Override
	public Class<Cliente> getDomainType() {
		// TODO Auto-generated method stub
		return Cliente.class;
	}

	@Override
	public String getId(Cliente domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdCliente();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Cliente domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}
	
	@Override
	public boolean isLive(Cliente domainObject) {
	    // Can't us Class.asSubclass() in web-mode code
	    return true;
	  }

}
