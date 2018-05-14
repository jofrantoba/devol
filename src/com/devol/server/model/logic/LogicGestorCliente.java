package com.devol.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.GestorCliente;
import com.devol.server.model.dao.DaoGestorCliente;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;

public class LogicGestorCliente {
	private PersistenceManager pm;

	public LogicGestorCliente(PersistenceManager pm) {
		this.pm = pm;
	}
	
	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		DaoGestorCliente dao = new DaoGestorCliente(this.pm);
		return dao.mantenimiento(parametro);
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoGestorCliente dao = new DaoGestorCliente(this.pm);
		return dao.mantenimiento(parametro);
	}

	public GestorCliente getBean(String id) throws UnknownException {
		DaoGestorCliente dao = new DaoGestorCliente(this.pm);
		return dao.getBean(id);
	}
	
	public Long desactivarGestorClienteByCobrador(String idUsuarioCobrador){		
		DaoGestorCliente dao = new DaoGestorCliente(this.pm);
		return dao.desactivarGestorClienteByCobrador(idUsuarioCobrador);
	}

	public Collection<GestorCliente> getListarBean() throws UnknownException {
		DaoGestorCliente dao = new DaoGestorCliente(this.pm);
		Collection<GestorCliente> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<GestorCliente> getListarBeanByCobrador(String idUsuarioCobrador) throws UnknownException {
		DaoGestorCliente dao = new DaoGestorCliente(this.pm);
		Collection<GestorCliente> lista = dao.getListarBeanByCobrador(idUsuarioCobrador);
		return lista;
	}
	
	public GestorCliente getGestorClienteActivo(String idUsuarioOwner,String idUsuarioCobrador) throws UnknownException {
		DaoGestorCliente dao = new DaoGestorCliente(this.pm);
		GestorCliente bean = dao.getGestorClienteActivo(idUsuarioOwner,idUsuarioCobrador);
		return bean;
	}
}
