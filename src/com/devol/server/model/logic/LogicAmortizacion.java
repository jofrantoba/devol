package com.devol.server.model.logic;

import java.util.Collection;
import java.util.Date;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.Amortizacion;
import com.devol.server.model.dao.DaoAmortizacion;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.KeyFactory;

public class LogicAmortizacion {
	private PersistenceManager pm;

	public LogicAmortizacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoAmortizacion dao = new DaoAmortizacion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoAmortizacion dao = new DaoAmortizacion(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}

	public Collection<Amortizacion> getListarBean() throws UnknownException {
		DaoAmortizacion dao = new DaoAmortizacion(this.pm);
		Collection<Amortizacion> lista = dao.getListarBean();
		return lista;
	}

	public Collection<Amortizacion> getListarBeanByPrestamo(String idPrestamo) throws UnknownException {
		DaoAmortizacion dao = new DaoAmortizacion(this.pm);
		Collection<Amortizacion> lista = dao.getListarBeanByPrestamo(idPrestamo);
		return lista;
	}
	
	public Collection<Amortizacion> getListarBeanByDate(String idUsuario,Date fechaIni) throws UnknownException {
		DaoAmortizacion dao = new DaoAmortizacion(this.pm);
		Collection<Amortizacion> lista = dao.getListarBeanByDate(idUsuario,fechaIni);
		return lista;
	}
}
