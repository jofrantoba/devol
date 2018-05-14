package com.devol.server.model.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.devol.server.model.bean.Cliente;
import com.devol.server.model.bean.GestorCliente;
import com.devol.server.model.bean.GestorCobranza;
import com.devol.server.model.bean.Usuario;
import com.devol.server.model.dao.PMF;
import com.devol.server.model.logic.LogicCliente;
import com.devol.server.model.logic.LogicGestorCliente;
import com.devol.server.model.logic.LogicGestorCobranza;
import com.devol.server.model.logic.LogicUsuario;
import com.devol.shared.AESencrypt;
import com.devol.shared.BeanParametro;
import com.devol.shared.Rol;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.KeyFactory;

public class GestionCobranza {
	private static final Logger LOG = Logger.getLogger(GestionCobranza.class
			.getName());	
	
	public static Boolean insertarCobrador(GestorCobranza bean)throws UnknownException{
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdUsuarioOwner() != null) {
		PersistenceManager pm = null;
		Transaction tx = null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			Date fecha=new Date();
			LogicUsuario logicUser = new LogicUsuario(pm);
			BeanParametro parametro = new BeanParametro();
			Usuario beanUserOwner = (Usuario) logicUser.getBean(bean.getIdUsuarioOwner());
			LogicGestorCobranza logicGestorCobranza=new LogicGestorCobranza(pm);
			if(beanUserOwner!=null){
				Usuario beanCobrador=null;
				if(bean.getIdUsuarioCobrador()!=null && !bean.getIdUsuarioCobrador().isEmpty()){
					beanCobrador=(Usuario) logicUser.getBean(bean.getIdUsuarioCobrador());
					if(beanCobrador==null){
						beanCobrador=(Usuario)logicUser.existeUsuario(bean.getBeanUsuarioCobrador().getCorreo());
					}
					if(beanCobrador!=null){
						if(beanUserOwner.getIdUsuario().equals(beanCobrador.getIdUsuario())){
							throw new UnknownException("Usuario es dueño");
						}
						if(logicGestorCobranza.getGestoCobranzaActivo(beanUserOwner.getIdUsuario(),beanCobrador.getIdUsuario())!=null){
							throw new UnknownException("Cobrador esta activo");
						}
						beanCobrador.setDni(bean.getBeanUsuarioCobrador().getDni());
						beanCobrador.setDireccion(bean.getBeanUsuarioCobrador().getDireccion());
						beanCobrador.setTelefono(bean.getBeanUsuarioCobrador().getTelefono());
						beanCobrador.setIsRolGestorCobranza(true);
						beanCobrador.getRoles().add(Rol.GESTORCOBRANZA.name());
						parametro.setBean(beanCobrador);
						parametro.setTipoOperacion("I");
						if(!logicUser.mantenimiento(parametro)){
							return false;
						}
					}
				}
				if(beanCobrador==null){
					beanCobrador=new Usuario();
					String correo=bean.getBeanUsuarioCobrador().getCorreo().trim().toLowerCase();
					beanCobrador.setIdCreateUsuario(correo);
					beanCobrador.setCorreo(correo);
					beanCobrador.setApellidos(bean.getBeanUsuarioCobrador().getApellidos());
					beanCobrador.setNombres(bean.getBeanUsuarioCobrador().getNombres());
					beanCobrador.setEstadoCuenta("P");
					beanCobrador.setDni(bean.getBeanUsuarioCobrador().getDni());
					beanCobrador.setDireccion(bean.getBeanUsuarioCobrador().getDireccion());
					beanCobrador.setTelefono(bean.getBeanUsuarioCobrador().getTelefono());
					beanCobrador.setIsRolGestorCobranza(true);
					beanCobrador.getRoles().add(Rol.GESTORCOBRANZA.name());
					String clave=bean.getBeanUsuarioCobrador().getDni();
					String encoded=AESencrypt.encrypt(clave);
					beanCobrador.setClave(encoded);
					beanCobrador.setOperacion("I");
					beanCobrador.setVersion(fecha.getTime());					
					parametro.setBean(beanCobrador);
					parametro.setTipoOperacion(beanCobrador.getOperacion());
					Boolean resultado1 = logicUser.mantenimiento(parametro);
					Boolean resultado2 = GestionUsuario.enviarMsgValidarCuenta(beanCobrador.getCorreo(),clave,beanCobrador.getNombres(),beanCobrador.getApellidos());
					if (!resultado1 || !resultado2) {
						tx.rollback();
						pm.close();
						return false;
					} 
				}
				
				bean.setIdGestorCobranza(KeyFactory.keyToString(KeyFactory.createKey(GestorCobranza.class.getSimpleName(), java.util.UUID.randomUUID().toString())));
				bean.setBeanUsuarioOwner(beanUserOwner);
				bean.setIdUsuarioOwner(beanUserOwner.getIdUsuario());
				bean.setBeanUsuarioCobrador(beanCobrador);
				bean.setIdUsuarioCobrador(beanCobrador.getIdUsuario());
				bean.setEstado("A");
				bean.setFechaInicio(fecha);
				bean.setOperacion("I");
				bean.setVersion(fecha.getTime());
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());				
				Boolean resultado3=logicGestorCobranza.mantenimiento(parametro);
				if (resultado3) {
					tx.commit();
					pm.close();
					return true;
				} else {
					tx.rollback();
					pm.close();
					return false;
				}							
			}else{
				throw new UnknownException("Usuario creador  no existe");
			}
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
		}else{
			throw new UnknownException("Verifique Catalogo de Servicio");
		}
	}
	
	public static Boolean desactivarGestorCliente(String idGestorCliente)throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx = null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			Date fecha=new Date();
			LogicGestorCliente logicGestorCliente=new LogicGestorCliente(pm);
			GestorCliente beanGestorCliente=logicGestorCliente.getBean(idGestorCliente);
			if(beanGestorCliente.getEstado().equalsIgnoreCase("D")){
				throw new UnknownException("Cliente ya esta desactivado");
			}				
			beanGestorCliente.setFechaFin(fecha);
			beanGestorCliente.setVersion(fecha.getTime());
			beanGestorCliente.setEstado("D");
			beanGestorCliente.getBeanCliente().setClienteAsignado(0);						
			tx.commit();
			return true;
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
	
	public static Boolean desactivarGestorCobranza(String idGestorCobranza)throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx = null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			tx = pm.currentTransaction();
			tx.begin();
			Date fecha=new Date();
			LogicGestorCobranza logicGestorCobranza=new LogicGestorCobranza(pm);
			GestorCobranza bean=logicGestorCobranza.getBean(idGestorCobranza);
			if(bean.getEstado().equalsIgnoreCase("D")){
				throw new UnknownException("Cobrador ya esta desactivado");
			}
			LogicGestorCliente logicGestorCliente=new LogicGestorCliente(pm);
			List<GestorCliente> listaGestorCliente=(List<GestorCliente>)logicGestorCliente.getListarBeanByCobrador(bean.getIdUsuarioCobrador());
			Iterator<GestorCliente> iterador=listaGestorCliente.iterator();
			while(iterador.hasNext()){
				GestorCliente beanGestorCliente=iterador.next();
				beanGestorCliente.setFechaFin(fecha);
				beanGestorCliente.setVersion(fecha.getTime());
				beanGestorCliente.setEstado("D");
				beanGestorCliente.getBeanCliente().setClienteAsignado(0);
			}
			//logicGestorCliente.desactivarGestorClienteByCobrador(bean.getIdUsuarioCobrador());
			bean.setFechaFin(fecha);
			bean.setEstado("D");
			bean.setVersion(fecha.getTime());
			tx.commit();
			return true;
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}					
	}
	
	public static Boolean asignarClientesAlCobrador(Set<Cliente> lista,String idUsuarioOwner,String idUsuarioCobrador,String idGestorCobranza) throws UnknownException{
		PersistenceManager pm = null;
		Transaction tx = null;
		try{			
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Cliente.class, "ClienteGroup").addMembers("beanUsuario");
			pm.getFetchPlan().addGroup("ClienteGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();					
			pm.setDetachAllOnCommit(true);			
			Date fecha=new Date();
			LogicUsuario logicUser = new LogicUsuario(pm);			
			Usuario beanUserCobrador = (Usuario) pm.detachCopy(logicUser.getBean(idUsuarioCobrador));
			LogicGestorCobranza logicGc=new LogicGestorCobranza(pm);
			GestorCobranza beanGc=(GestorCobranza) pm.detachCopy(logicGc.getBean(idGestorCobranza));
			if(beanGc.getEstado().equalsIgnoreCase("D")){
				throw new UnknownException("cobrador esta desactivado");
			}
			Iterator<Cliente> i=lista.iterator();
			List<String> idListCliente=new ArrayList<String>();
			while(i.hasNext()){
				Cliente bean=i.next();
				idListCliente.add(bean.getIdCliente());				
			}
			LogicCliente logicCliente=new LogicCliente(pm);			
			List<Cliente> listaCliente=(List<Cliente>)pm.detachCopyAll(logicCliente.getListarBeanIn(idListCliente));
			Iterator<Cliente> iterador=listaCliente.iterator();
			List<BeanParametro> param=new ArrayList<BeanParametro>();
			while(iterador.hasNext()){
				Cliente beanCliente=pm.detachCopy(iterador.next());
				beanCliente.setClienteAsignado(1);
				Usuario beanUsuario=pm.detachCopy(beanCliente.getBeanUsuario());
				GestorCliente gc=new GestorCliente();
				gc.setIdGestorCliente(KeyFactory.keyToString(KeyFactory.createKey(GestorCliente.class.getSimpleName(), java.util.UUID.randomUUID().toString())));
				gc.setBeanCliente(beanCliente);
				gc.setIdCliente(beanCliente.getIdCliente());
				gc.setBeanGestorCobranza(beanGc);
				gc.setIdGestorCobranza(beanGc.getIdGestorCobranza());
				gc.setBeanUsuarioCobrador(beanUserCobrador);
				gc.setIdUsuarioCobrador(beanUserCobrador.getIdUsuario());
				gc.setBeanUsuarioOwner(beanUsuario);
				gc.setIdUsuarioOwner(beanUsuario.getIdUsuario());
				gc.setEstado("A");
				gc.setFechaInicio(fecha);
				gc.setVersion(fecha.getTime());
				gc.setOperacion("I");
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(gc);
				parametro.setTipoOperacion("I");
				param.add(parametro);
			}												
			LogicGestorCliente logicGestorCliente=new LogicGestorCliente(pm);
			Boolean resultado= logicGestorCliente.mantenimiento(param);		
			if (resultado) {
				tx.commit();
				pm.close();
				return true;
			} else {
				tx.rollback();
				pm.close();
				return false;
			}
		}catch(Exception ex){
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}finally{
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
			
			
	}
	
	public static List<GestorCobranza> listarGestorCobranzaByUsuario(String idUsuario) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			pm.getFetchGroup(GestorCobranza.class, "GestorCobranzaGroup").addMember("beanUsuarioCobrador");
			pm.getFetchPlan().addGroup("GestorCobranzaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicGestorCobranza logic = new LogicGestorCobranza(pm);
			List<GestorCobranza> resultado = (List<GestorCobranza>) pm.detachCopyAll(logic
					.getListarBeanByUsuario(idUsuario));			
			  tx.commit();
			return resultado;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}

	}
	
	public static List<GestorCobranza> listarPrestamistaByCobrador(String idUsuarioCobrador) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			pm.getFetchGroup(GestorCobranza.class, "GestorCobranzaGroup").addMember("beanUsuarioOwner");
			pm.getFetchPlan().addGroup("GestorCobranzaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicGestorCobranza logic = new LogicGestorCobranza(pm);
			List<GestorCobranza> resultado = (List<GestorCobranza>) pm.detachCopyAll(logic
					.getListarBeanByCobrador(idUsuarioCobrador));			
			  tx.commit();
			return resultado;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}

	}
	
	public static List<Cliente> listarClienteSinCobrador(String idUsuarioOwner)throws UnknownException{
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicCliente logic = new LogicCliente(pm);
			List<Cliente> resultado = (List<Cliente>) logic.getListarBeanByUsuarioSinCobrador(idUsuarioOwner);
			pm.close();
			return resultado;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	public static List<Cliente> listarGestorClienteByCobrador(String idUsuarioCobrador) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;		
		try {
			pm = PMF.getPMF().getPersistenceManager();
			pm.getFetchGroup(GestorCliente.class, "GestorClienteGroup").addMember("beanCliente");
			pm.getFetchPlan().addGroup("GestorClienteGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicGestorCliente logic = new LogicGestorCliente(pm);
			List<GestorCliente> resultado = (List<GestorCliente>) pm.detachCopyAll(logic.getListarBeanByCobrador(idUsuarioCobrador));
			Iterator<GestorCliente> iterador=resultado.iterator();
			List<Cliente> listaGestorCliente=new ArrayList<Cliente>();
			while(iterador.hasNext()){
				GestorCliente beanGestorCliente=pm.detachCopy(iterador.next());
				Cliente beanCliente=pm.detachCopy(beanGestorCliente.getBeanCliente());
				beanCliente.setIdGestorCliente(beanGestorCliente.getIdGestorCliente());
				beanCliente.setIdGestorCobranza(beanGestorCliente.getIdGestorCobranza());
				beanCliente.setIdUsuarioCobrador(beanGestorCliente.getIdUsuarioCobrador());
				beanCliente.setIdUsuarioOwner(beanGestorCliente.getIdUsuarioOwner());
				listaGestorCliente.add(beanCliente);
			}
			tx.commit();
			return listaGestorCliente;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}

	}
	
	
	
	
		

}
