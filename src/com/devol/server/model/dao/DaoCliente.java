package com.devol.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import java.util.ArrayList;
import java.util.List;

import com.devol.server.model.bean.Cliente;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.Key;

public class DaoCliente {
	private PersistenceManager pm;

	public DaoCliente(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Cliente getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return (Cliente)query.getBean(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Cliente> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Cliente> lista = (Collection<Cliente>) query
				.getListaBean(Cliente.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Cliente> getListarBeanByUsuario(String idUsuario) throws UnknownException {
		Query query = pm.newQuery(Cliente.class);
		query.setFilter("idUsuario == paramIdUsuario");
		query.setOrdering("version desc");
		query.declareParameters("String paramIdUsuario");
		try{
		List<Cliente> lista=new ArrayList<Cliente>();
		lista.addAll((List<Cliente>)query.execute(idUsuario));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Cliente> getListarBeanByUsuarioSinCobrador(String idUsuarioOwner) throws UnknownException {
		//Query query = pm.newQuery("select from com.devol.server.model.bean.Cliente WHERE this.idUsuario == :paramIdUsuario && this.clienteAsignado < :paramAsignado");			
		Query query = pm.newQuery(Cliente.class);		
		query.setFilter("idUsuario == paramIdUsuario && clienteAsignado == paramAsignado");
		query.setOrdering("clienteAsignado,nombre,apellido desc");
		query.declareParameters("String paramIdUsuario, Integer paramAsignado");
		try{
		List<Cliente> lista=new ArrayList<Cliente>();
		Integer paramAsignado=new Integer(0);
		lista.addAll((List<Cliente>)query.execute(idUsuarioOwner,paramAsignado));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Cliente> getListarBeanIn(List<String> listaIdCliente) throws UnknownException {
		Query query = pm.newQuery(Cliente.class);
		query.setFilter("paramIdListCliente.contains(idCliente)");
		query.declareImports("import java.util.List;");
		query.declareParameters("List paramIdListCliente");
		try{
		List<Cliente> lista=new ArrayList<Cliente>();		
		lista.addAll((List<Cliente>)query.execute(listaIdCliente));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
