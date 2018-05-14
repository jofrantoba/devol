package com.devol.server.model.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.commons.lang3.StringUtils;

import com.devol.server.model.bean.Usuario;
import com.devol.shared.BeanParametro;
import com.devol.shared.SharedUtil;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.Key;

public class DaoUsuario {
	private PersistenceManager pm;

	public DaoUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Usuario> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Usuario> lista = (Collection<Usuario>) query
				.getListaBean(Usuario.class);
		return lista;
	}
	
	public Object existeUsuario(String correo){
		correo=correo.trim();
		Query query=pm.newQuery(Usuario.class);
		String declareParameters="String paramCorreoNormal,String paramCorreoMinus,String paramCorreoMayus,String paramCorreoPrimeraLetraMayus";
		query.declareParameters(declareParameters);
		query.setFilter("correo==paramCorreoNormal || correo==paramCorreoMinus || correo==paramCorreoMayus || correo==paramCorreoPrimeraLetraMayus");		
		List<Usuario> results = (List<Usuario>)query.executeWithArray(correo,correo.toLowerCase(),correo.toUpperCase(),SharedUtil.firstLetterMayus(correo));		
		if(results.size()>0){
			return results.get(0);
		}else{
			return null;
		}		
	}
}
