package com.devol.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;


public class Querys {
	
	private PersistenceManager pm;
	private static final Logger LOG = Logger.getLogger(Querys.class.getName());

	public Querys(){		
	}
	
	public Querys(PersistenceManager cnx){
		pm = cnx;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean mantenimiento(Collection<BeanParametro> parametros) throws UnknownException{
		try{
			Iterator<BeanParametro> iterador=parametros.iterator();
			BeanParametro parametro;
			Collection objectsIU=new ArrayList();
			Collection objectsE=new ArrayList();
			while(iterador.hasNext()){
				parametro=iterador.next();
				if(parametro.getTipoOperacion().equalsIgnoreCase("I") || parametro.getTipoOperacion().equalsIgnoreCase("A")){
					objectsIU.add(parametro.getBean());											
				}else if(parametro.getTipoOperacion().equalsIgnoreCase("E")){
					Object bean = pm.getObjectById(parametro.getBean().getClass(),parametro.getId());
					objectsE.add(bean);															
				}else{
					throw new UnknownException("NO se ha definido Operacion");
				}
			}
			if(objectsIU.size()>0){
				pm.makePersistentAll(objectsIU);
			}
			if(objectsE.size()>0){
				pm.deletePersistentAll(objectsE);
			}
			return true;
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}
	
	public boolean mantenimiento(BeanParametro parametro) throws UnknownException{
		try{
			if(parametro.getTipoOperacion().equalsIgnoreCase("I")){
				pm.makePersistent(parametro.getBean());
				return true;
			}else if(parametro.getTipoOperacion().equalsIgnoreCase("A")){
				pm.makePersistent(parametro.getBean());
				return true;
			}else if(parametro.getTipoOperacion().equalsIgnoreCase("E")){
				Object bean = pm.getObjectById(parametro.getBean().getClass(),parametro.getId());
				pm.deletePersistent(bean);
				return true;
			}else{
				throw new UnknownException("NO se ha definido Operacion");
			}
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}
	
	public Object getBean(Class<?> nomClase, Long id) throws UnknownException{
		try{
			Object bean = this.pm.getObjectById(nomClase,id);
			return bean;
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}
	
	public Object getBean(Class<?> nomClase, String id) throws UnknownException{
		try{
			Object bean = this.pm.getObjectById(nomClase,id);
			return bean;
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}
	
	public Object getBean(Class<?> nomClase, Key id) throws UnknownException{
		try{
			Object bean = this.pm.getObjectById(nomClase,id);
			return bean;
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<?> getListaBean(Class<?> nomClase) throws UnknownException{
		try{
			Extent<?> extent = this.pm.getExtent(nomClase,false);
			Iterator<?> iterador = extent.iterator();
			ArrayList lista = new ArrayList();
			while(iterador.hasNext()){			
				lista.add(iterador.next());
			}
			extent.closeAll();
			return lista;
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}
}
