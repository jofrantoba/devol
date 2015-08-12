package com.devol.server.model.process;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.datanucleus.FetchGroup;
import org.datanucleus.FetchPlan;

import com.devol.server.model.bean.Cliente;
import com.devol.server.model.bean.Usuario;
import com.devol.server.model.dao.PMF;
import com.devol.server.model.logic.LogicCliente;
import com.devol.server.model.logic.LogicUsuario;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;

public class GestionCliente {
	private static final Logger LOG = Logger.getLogger(GestionCliente.class
			.getName());

	public static Boolean insertarCliente(Cliente bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I") && bean.getDni() != null
				&& bean.getIdUsuario() != null) {
			BeanParametro parametro = new BeanParametro();			
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				LogicUsuario logicUser = new LogicUsuario(pm);
				Usuario beanUser = (Usuario) logicUser.getBean(bean
						.getIdUsuario());						
				bean.setBeanUsuario(beanUser);		
				beanUser.getListCliente().add(bean);
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				//System.out.println(beanUser.getListCliente().size());
				LogicCliente logic = new LogicCliente(pm);
				Boolean resultado = logic.mantenimiento(parametro);
				if (resultado) {
					tx.commit();					
					pm.close();
					return true;
				} else {
					tx.rollback();
					pm.close();
					return false;
				}
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
		} else {
			throw new UnknownException("Verifique Catalogo de Servicio");
		}
	}

	public static Boolean actualizarCliente(Cliente bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A") && bean.getDni() != null
				&& bean.getIdUsuario() != null && bean.getIdCliente()!=null) {

			BeanParametro parametro = new BeanParametro();
			parametro.setBean(bean);
			parametro.setTipoOperacion(bean.getOperacion());
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				LogicCliente logic = new LogicCliente(pm);
				Boolean resultado = logic.mantenimiento(parametro);
				if (resultado) {
					tx.commit();
					pm.close();
					return true;
				} else {
					tx.rollback();
					pm.close();
					return false;
				}
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
		} else {
			throw new UnknownException("Verifique Catalogo de Servicio");
		}
	}

	public static Boolean eliminarCliente(Cliente bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") && bean.getDni() != null
				&& bean.getIdUsuario() != null && bean.getIdCliente()!=null) {
			if(bean.getNumPrestamo()==0){						
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(bean);
			parametro.setId(bean.getIdCliente());
			parametro.setTipoOperacion(bean.getOperacion());
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				LogicCliente logic = new LogicCliente(pm);
				Boolean resultado = logic.mantenimiento(parametro);
				if (resultado) {
					tx.commit();
					pm.close();
					return true;
				} else {
					tx.rollback();
					pm.close();
					return false;
				}
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
			}else{
				throw new UnknownException("Cliente tiene prestamos");
			}
		} else {
			throw new UnknownException("Verifique Catalogo de Servicio");
		}
	}

	public static List<Cliente> listarCliente() throws UnknownException {

		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicCliente logic = new LogicCliente(pm);
			List<Cliente> resultado = (List<Cliente>) logic.getListarBean();
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
	
	public static List<Cliente> listarClienteByUsuario(String idUsuario) throws UnknownException {

		PersistenceManager pm = null;
		//Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();						
			//PMF.getPMF().getFetchGroup(Cliente.class, "ClienteGroup").addMember("beanUsuario");
			//pm.getFetchPlan().addGroup("ClienteGroup");
			//pm.getFetchPlan().setMaxFetchDepth(1);
			//tx = pm.currentTransaction();
			//tx.begin();
			//pm.setDetachAllOnCommit(true);
			LogicCliente logic = new LogicCliente(pm);
			//List<Cliente> resultado = (List<Cliente>)pm.detachCopyAll(logic.getListarBeanByUsuario(idUsuario));
			List<Cliente> resultado = (List<Cliente>)logic.getListarBeanByUsuario(idUsuario);
			/*Iterator<Cliente> i=resultado.iterator();
			while(i.hasNext()){
				Cliente c=i.next();
				System.out.println(c.getListPrestamo().size());
			}*/			
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

}
