package com.devol.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.GestorCobranza;
import com.devol.server.model.dao.DaoGestorCobranza;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;

public class LogicGestorCobranza {
	private PersistenceManager pm;

	public LogicGestorCobranza(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoGestorCobranza dao = new DaoGestorCobranza(this.pm);
		return dao.mantenimiento(parametro);
	}

	public GestorCobranza getBean(String id) throws UnknownException {
		DaoGestorCobranza dao = new DaoGestorCobranza(this.pm);
		return dao.getBean(id);
	}

	public Collection<GestorCobranza> getListarBean() throws UnknownException {
		DaoGestorCobranza dao = new DaoGestorCobranza(this.pm);
		Collection<GestorCobranza> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<GestorCobranza> getListarBeanByUsuario(String idUsuario) throws UnknownException {
		DaoGestorCobranza dao = new DaoGestorCobranza(this.pm);
		Collection<GestorCobranza> lista = dao.getListarBeanByUsuario(idUsuario);
		return lista;
	}
	
	public Collection<GestorCobranza> getListarBeanByCobrador(String idUsuarioCobrador) throws UnknownException {
		DaoGestorCobranza dao = new DaoGestorCobranza(this.pm);
		Collection<GestorCobranza> lista = dao.getListarBeanByCobrador(idUsuarioCobrador);
		return lista;
	}
	
	public GestorCobranza getGestoCobranzaActivo(String idUsuarioOwner,String idUsuarioCobrador) throws UnknownException {
		DaoGestorCobranza dao = new DaoGestorCobranza(this.pm);
		GestorCobranza bean = dao.getGestoCobranzaActivo(idUsuarioOwner,idUsuarioCobrador);
		return bean;
	}
}
