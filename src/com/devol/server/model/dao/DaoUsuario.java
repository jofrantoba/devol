package com.devol.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.Usuario;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.Key;

public class DaoUsuario {
	private PersistenceManager pm;

	public DaoUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Usuario> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Usuario> lista = (Collection<Usuario>) query
				.getListaBean(Usuario.class);
		return lista;
	}
}
