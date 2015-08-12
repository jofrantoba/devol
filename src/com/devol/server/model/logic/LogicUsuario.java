package com.devol.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.Usuario;
import com.devol.server.model.dao.DaoUsuario;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class LogicUsuario {
	private PersistenceManager pm;

	public LogicUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuario dao = new DaoUsuario(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoUsuario dao = new DaoUsuario(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}
	
	public Object getBeanCorreo(String correo) throws UnknownException{
		DaoUsuario dao = new DaoUsuario(this.pm);
		return dao.getBean(KeyFactory.createKey(Usuario.class.getSimpleName(),correo));				
	}

	
	public Object getBean(Key id) throws UnknownException {
		DaoUsuario dao = new DaoUsuario(this.pm);
		return dao.getBean(id);
	}

	public Collection<Usuario> getListarBean() throws UnknownException {
		DaoUsuario dao = new DaoUsuario(this.pm);
		Collection<Usuario> lista = dao.getListarBean();
		return lista;
	}
}
