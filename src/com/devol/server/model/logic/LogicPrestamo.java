package com.devol.server.model.logic;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.devol.server.model.bean.Prestamo;
import com.devol.server.model.dao.DaoPrestamo;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.KeyFactory;

public class LogicPrestamo {
	private PersistenceManager pm;

	public LogicPrestamo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPrestamo dao = new DaoPrestamo(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoPrestamo dao = new DaoPrestamo(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}

	public Collection<Prestamo> getListarBean() throws UnknownException {
		DaoPrestamo dao = new DaoPrestamo(this.pm);
		Collection<Prestamo> lista = dao.getListarBean();
		return lista;
	}

	public Collection<Prestamo> getListarBeanByUsuario(String idUsuario,
			String estado) throws UnknownException {
		DaoPrestamo dao = new DaoPrestamo(this.pm);
		Collection<Prestamo> lista = dao.getListarBeanByUsuario(idUsuario,
				estado);
		return lista;
	}
	
	public void asignarGestorClientePrestamo(String idGestorCliente,String idGestorCobranza,String idCliente){
		DaoPrestamo dao = new DaoPrestamo(this.pm);
		dao.asignarGestorClientePrestamo(idGestorCliente, idGestorCobranza, idCliente);
	}
	
	public void desactivarGestorClientePrestamo(String idCliente){
		DaoPrestamo dao = new DaoPrestamo(this.pm);
		dao.desactivarGestorClientePrestamo(idCliente);
	}
	
	public Collection<Prestamo> getListarByClientes(List<String> listaIdCliente,String estado) throws UnknownException {
		DaoPrestamo dao = new DaoPrestamo(this.pm);
		Collection<Prestamo> lista = dao.getListarByClientes(listaIdCliente, estado);
		return lista;
	}
}
