package com.devol.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.devol.server.model.bean.GestorCliente;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;

public class DaoGestorCliente {
	private PersistenceManager pm;

	public DaoGestorCliente(PersistenceManager pm) {
		this.pm = pm;
	}
	
	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public GestorCliente getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (GestorCliente)query.getBean(GestorCliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<GestorCliente> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<GestorCliente> lista = (Collection<GestorCliente>) query
				.getListaBean(GestorCliente.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<GestorCliente> getListarBeanByCobrador(String idUsuarioCobrador) throws UnknownException {
		Query query = pm.newQuery(GestorCliente.class);
		query.setFilter("idUsuarioCobrador == paramIdUsuarioCobrador && estado==paramEstado");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdUsuarioCobrador,String paramEstado");
		try{
		List<GestorCliente> lista=new ArrayList<GestorCliente>();
		lista.addAll((List<GestorCliente>)query.execute(idUsuarioCobrador,"A"));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<GestorCliente> getListarBeanByGestorCobranza(String idGestorCobranza) throws UnknownException {
		Query query = pm.newQuery(GestorCliente.class);
		query.setFilter("idGestorCobranza == paramIdGestorCobranza && estado==paramEstado");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdGestorCobranza,String paramEstado");
		try{
		List<GestorCliente> lista=new ArrayList<GestorCliente>();
		lista.addAll((List<GestorCliente>)query.execute(idGestorCobranza,"A"));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<String> getIdClienteByCobrador(String idUsuarioCobrador) throws UnknownException {
		Query query = pm.newQuery(GestorCliente.class);
		query.setFilter("idUsuarioCobrador == paramIdUsuarioCobrador && estado==paramEstado");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdUsuarioCobrador,String paramEstado");
		query.setResult("idCliente");
		try{
		List lista=new ArrayList();
		lista.addAll((List<GestorCliente>)query.execute(idUsuarioCobrador,"A"));		
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<String> getIdClienteByGestorCobranza(String idGestorCobranza) throws UnknownException {
		Query query = pm.newQuery(GestorCliente.class);
		query.setFilter("idGestorCobranza == paramIdGestorCobranza && estado==paramEstado");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdGestorCobranza,String paramEstado");
		query.setResult("idCliente");
		try{
		List lista=new ArrayList();
		lista.addAll((List<GestorCliente>)query.execute(idGestorCobranza,"A"));		
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	public Long desactivarGestorClienteByCobrador(String idUsuarioCobrador){		
		Query query = pm.newQuery("UPDATE com.devol.server.model.bean.GestorCliente SET this.fechaFin==:paramFechaFin,this.estado==:paramEstado WHERE this.idGestorCobranza == :paramIdGestorCobranza");
		Long number = (Long)query.execute(new java.util.Date(),"D",idUsuarioCobrador);
		return number;
	}
	
	@SuppressWarnings("unchecked")
	public GestorCliente getGestorClienteActivo(String idUsuarioCobrador,String idCliente) throws UnknownException {
		Query query = pm.newQuery(GestorCliente.class);
		query.setFilter("idUsuarioCobrador == paramIdUsuarioCobrador && idCliente==paramIdCliente && estado==paramEstado");		
		query.declareParameters("String paramIdUsuarioCobrador,String paramIdCliente,String paramEstado");
		try{		
		List<GestorCliente> lista=new ArrayList<GestorCliente>();
		lista.addAll((List<GestorCliente>)query.execute(idUsuarioCobrador,idCliente,"A"));
		if(lista.isEmpty()){
			return null;
		}else if(lista.size()>1){
			throw new UnknownException("El cobrador tiene varias configuraciones");
		}else{
			return lista.get(0);
		}		
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
