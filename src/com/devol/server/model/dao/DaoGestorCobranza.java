package com.devol.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.devol.server.model.bean.GestorCobranza;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;

public class DaoGestorCobranza {
	private PersistenceManager pm;

	public DaoGestorCobranza(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public GestorCobranza getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (GestorCobranza)query.getBean(GestorCobranza.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<GestorCobranza> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<GestorCobranza> lista = (Collection<GestorCobranza>) query
				.getListaBean(GestorCobranza.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<GestorCobranza> getListarBeanByUsuario(String idUsuarioOwner) throws UnknownException {
		Query query = pm.newQuery(GestorCobranza.class);
		query.setFilter("idUsuarioOwner == paramIdUsuarioOwner && estado==paramEstado");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdUsuarioOwner,String paramEstado");
		try{
		List<GestorCobranza> lista=new ArrayList<GestorCobranza>();
		lista.addAll((List<GestorCobranza>)query.execute(idUsuarioOwner,"A"));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<GestorCobranza> getListarBeanByCobrador(String idUsuarioCobrador) throws UnknownException {
		Query query = pm.newQuery(GestorCobranza.class);
		query.setFilter("idUsuarioCobrador == paramIdUsuarioCobrador && estado==paramEstado");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdUsuarioCobrador,String paramEstado");
		try{
		List<GestorCobranza> lista=new ArrayList<GestorCobranza>();
		lista.addAll((List<GestorCobranza>)query.execute(idUsuarioCobrador,"A"));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public GestorCobranza getGestoCobranzaActivo(String idUsuarioOwner,String idUsuarioCobrador) throws UnknownException {
		Query query = pm.newQuery(GestorCobranza.class);
		query.setFilter("idUsuarioOwner == paramIdUsuarioOwner && idUsuarioCobrador==paramIdUsuarioCobrador && estado==paramEstado");		
		query.declareParameters("String paramIdUsuarioOwner,String paramIdUsuarioCobrador,String paramEstado");
		try{		
		List<GestorCobranza> lista=new ArrayList<GestorCobranza>();
		lista.addAll((List<GestorCobranza>)query.execute(idUsuarioOwner,idUsuarioCobrador,"A"));
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
