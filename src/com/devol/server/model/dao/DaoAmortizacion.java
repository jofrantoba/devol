package com.devol.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.devol.server.model.bean.Amortizacion;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.Key;

public class DaoAmortizacion {
	private PersistenceManager pm;

	public DaoAmortizacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Amortizacion.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Amortizacion> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Amortizacion> lista = (Collection<Amortizacion>) query
				.getListaBean(Amortizacion.class);
		return lista;
	}
	
	public Collection<Amortizacion> getListarBeanByPrestamo(String idPrestamo) throws UnknownException {		
		Query query = pm.newQuery(Amortizacion.class);		
		query.setFilter("idPrestamo == paramIdPrestamo");		
		query.setOrdering("version desc");
		query.declareParameters("String paramIdPrestamo");		
		try{
		List<Amortizacion> lista=new ArrayList<Amortizacion>();
		lista.addAll((List<Amortizacion>)query.execute(idPrestamo));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	public Collection<Amortizacion> getListarBeanByDate(String idUsuario,Date fechaIni) throws UnknownException {		
		Query query = pm.newQuery(Amortizacion.class);		
		query.setFilter("idUsuario == paramIdUsuario && fecha == paramFecha");		
		//query.setOrdering("version desc");
		query.declareParameters("String paramIdUsuario,String paramFecha");		
		try{
		List<Amortizacion> lista=new ArrayList<Amortizacion>();
		lista.addAll((List<Amortizacion>)query.execute(idUsuario,fechaIni));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
