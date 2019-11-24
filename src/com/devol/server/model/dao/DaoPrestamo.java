package com.devol.server.model.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.devol.server.model.bean.Cliente;
import com.devol.server.model.bean.Prestamo;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.Key;

public class DaoPrestamo {
	private PersistenceManager pm;

	public DaoPrestamo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Prestamo.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Prestamo> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Prestamo> lista = (Collection<Prestamo>) query
				.getListaBean(Prestamo.class);
		return lista;
	}
	
	public Collection<Prestamo> getListarBeanByUsuario(String idUsuario,String estado) throws UnknownException {		
		Query query = pm.newQuery(Prestamo.class);		
		query.setFilter("idUsuario == paramIdUsuario && estado == paramEstado");		
		query.setOrdering("fecha desc");
		query.declareParameters("String paramIdUsuario,String paramEstado");		
		try{
		List<Prestamo> lista=new ArrayList<Prestamo>();
		lista.addAll((List<Prestamo>)query.execute(idUsuario,estado));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Prestamo> getListarByClientes(List<String> listaIdCliente,String estado) throws UnknownException {
		Query query = pm.newQuery(Prestamo.class);
		query.setFilter("paramIdListCliente.contains(idCliente) && estado == paramEstado");
		query.setOrdering("fecha desc");
		query.declareImports("import java.util.List;");
		query.declareParameters("List paramIdListCliente,String paramEstado");
		try{
		List<Prestamo> lista=new ArrayList<Prestamo>();		
		lista.addAll((List<Prestamo>)query.execute(listaIdCliente,estado));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	public void asignarGestorClientePrestamo(String idGestorCliente,String idGestorCobranza,String idCliente){		
		Query query = pm.newQuery("UPDATE com.devol.server.model.bean.Prestamo SET this.idGestorCobranza==:paramIdGestorCobranza,this.idGestorCliente==:paramIdGestorCliente WHERE this.idCliente == :paramIdCliente && this.estado==:paramEstado");
		Map params = new HashMap();
		params.put("paramIdGestorCobranza", idGestorCobranza);
		params.put("paramIdGestorCliente", idGestorCliente);
		params.put("paramIdCliente", idCliente);
		params.put("paramEstado", "P");
		query.execute(params);		
	}
	
	public void desactivarGestorClientePrestamo(String idCliente){		
		Query query = pm.newQuery("UPDATE com.devol.server.model.bean.Prestamo SET this.idGestorCobranza==:paramIdGestorCobranza,this.idGestorCliente==:paramIdGestorCliente WHERE this.idCliente == :paramIdCliente && this.estado==:paramEstado");
		Map params = new HashMap();
		params.put("paramIdGestorCobranza", null);
		params.put("paramIdGestorCliente", null);
		params.put("paramIdCliente", idCliente);
		params.put("paramEstado", "P");
		query.execute(params);		
	}
}
