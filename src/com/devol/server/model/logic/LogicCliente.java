package com.devol.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.Cliente;
import com.devol.server.model.dao.DaoCliente;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.KeyFactory;

public class LogicCliente {
	private PersistenceManager pm;

	public LogicCliente(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCliente dao = new DaoCliente(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCliente dao = new DaoCliente(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}

	public Collection<Cliente> getListarBean() throws UnknownException {
		DaoCliente dao = new DaoCliente(this.pm);
		Collection<Cliente> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Cliente> getListarBeanByUsuario(String idUsuario) throws UnknownException {
		DaoCliente dao = new DaoCliente(this.pm);
		Collection<Cliente> lista = dao.getListarBeanByUsuario(idUsuario);
		return lista;
	}
}
