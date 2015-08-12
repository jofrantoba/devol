package com.devol.server.model.process;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.devol.client.view.uihome.UIHome;
import com.devol.server.locator.LocatorPrestamo;
import com.devol.server.model.bean.Amortizacion;
import com.devol.server.model.bean.Cliente;
import com.devol.server.model.bean.Prestamo;
import com.devol.server.model.dao.PMF;
import com.devol.server.model.logic.LogicAmortizacion;
import com.devol.server.model.logic.LogicCliente;
import com.devol.server.model.logic.LogicPrestamo;
import com.devol.shared.BeanParametro;
import com.devol.shared.UnknownException;

public class GestionPrestamo {
	private static final Logger LOG = Logger.getLogger(GestionPrestamo.class
			.getName());

	public static Boolean insertarPrestamo(Prestamo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I") && bean.getDni() != null
				&& bean.getIdUsuario() != null && bean.getIdCliente()!=null) {
			BeanParametro parametro = new BeanParametro();
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				LogicCliente logicCliente = new LogicCliente(pm);
				Cliente beanCliente = (Cliente) logicCliente.getBean(bean
						.getIdCliente());
				if(beanCliente.getBeanUsuario().getEstadoCuenta().equals("P")){
					throw new UnknownException("Active su cuenta, revise su bandeja de correo");
				}
				Integer numPrestamo=beanCliente.getNumPrestamo()+1;
				beanCliente.setNumPrestamo(numPrestamo);
				bean.setBeanCliente(beanCliente);
				beanCliente.getListPrestamo().add(bean);
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				System.out.println(beanCliente.getListPrestamo().size());
				LogicPrestamo logic = new LogicPrestamo(pm);
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

	public static Boolean actualizarPrestamo(Prestamo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A") && bean.getDni() != null
				&& bean.getIdUsuario() != null && bean.getIdCliente()!=null
				&& bean.getIdPrestamo() != null) {
			BeanParametro parametro = new BeanParametro();			
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();				
				Boolean resultado=false;
				LogicPrestamo logic = new LogicPrestamo(pm);
				Prestamo beanAux=(Prestamo)logic.getBean(bean.getIdPrestamo());				
				if(beanAux.getBeanCliente().getIdCliente().equalsIgnoreCase(bean.getIdCliente())){
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				resultado= logic.mantenimiento(parametro);
				}else{
					Prestamo beanPrestamo=new Prestamo();
					LogicCliente logicCliente = new LogicCliente(pm);
					Cliente beanCliente = (Cliente) logicCliente.getBean(bean
							.getIdCliente());
					beanPrestamo.setIdPrestamo(beanCliente.getIdCliente());
					Integer numPrestamo=beanAux.getBeanCliente().getNumPrestamo()-1;
					beanAux.getBeanCliente().setNumPrestamo(numPrestamo);											
					Iterator<Amortizacion> iterador=beanAux.getListAmortizacion().iterator();
					Set<Amortizacion> amortizacionNew=new HashSet<Amortizacion>();
					while(iterador.hasNext()){
						Amortizacion beanAmortizacionOld=iterador.next();
						Amortizacion beanAmortizacionNew=new Amortizacion();
						beanAmortizacionNew.setOperacion("I");
						beanAmortizacionNew.setVersion(bean.getVersion());
						beanAmortizacionNew.setIdAmortizacion(beanPrestamo.getIdPrestamo());
						beanAmortizacionNew.setFecha(beanAmortizacionOld.getFecha());
						beanAmortizacionNew.setMonto(beanAmortizacionOld.getMonto());						
						beanAmortizacionNew.setIdUsuario(bean.getIdUsuario());
						amortizacionNew.add(beanAmortizacionNew);
					}
					parametro.setBean(beanAux);
					parametro.setId(beanAux.getIdPrestamo());
					parametro.setTipoOperacion("E");
					resultado= logic.mantenimiento(parametro);
					if(resultado){									
					numPrestamo=beanCliente.getNumPrestamo()+1;
					beanCliente.setNumPrestamo(numPrestamo);					
					beanPrestamo.setBeanCliente(beanCliente);										
					beanPrestamo.setVersion(bean.getVersion());
					
					beanPrestamo.setFecha(bean.getFecha());
					beanPrestamo.setMonto(bean.getMonto());
					beanPrestamo.setTasa(bean.getTasa());
					beanPrestamo.setaDevolver(bean.getaDevolver());
					beanPrestamo.setDevuelto(bean.getDevuelto());
					beanPrestamo.setEstado("P");
					beanPrestamo.setDni(bean.getDni());
					beanPrestamo.setNombre(bean.getNombre());
					beanPrestamo.setApellido(bean.getApellido());
					beanPrestamo.setBeanCliente(beanCliente);
					beanPrestamo.setIdUsuario(bean.getIdUsuario());					
					beanCliente.getListPrestamo().add(beanPrestamo);
					
					beanPrestamo.setListAmortizacion(amortizacionNew);
					parametro.setBean(beanPrestamo);
					parametro.setTipoOperacion("I");					
					System.out.println(beanCliente.getListPrestamo().size());					
					resultado = logic.mantenimiento(parametro);
					}
				}
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

	public static Boolean eliminarPrestamo(Prestamo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") && bean.getDni() != null
				&& bean.getIdUsuario() != null && bean.getIdCliente()!=null
				&& bean.getIdPrestamo() != null) {
			if(bean.getEstado().equals("P")){							
			if(BigDecimal.valueOf(bean.getDevuelto()).compareTo(BigDecimal.ZERO)==1){
				throw new UnknownException("Existen amortizaciones");
			}
			}
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(bean);
			parametro.setId(bean.getIdPrestamo());
			parametro.setTipoOperacion(bean.getOperacion());
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				LogicCliente logicCliente = new LogicCliente(pm);
				Cliente beanCliente = (Cliente) logicCliente.getBean(bean
						.getIdCliente());
				Integer numPrestamo=beanCliente.getNumPrestamo()-1;
				beanCliente.setNumPrestamo(numPrestamo);
				bean.setBeanCliente(beanCliente);
				LogicPrestamo logic = new LogicPrestamo(pm);
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

	public static List<Prestamo> listarPrestamo() throws UnknownException {

		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicPrestamo logic = new LogicPrestamo(pm);
			List<Prestamo> resultado = (List<Prestamo>) logic.getListarBean();
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

	public static List<Prestamo> listarPrestamoByUsuario(String idUsuario,
			String estado) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Prestamo.class, "PrestamoGroup").addMember("beanCliente");
			pm.getFetchPlan().addGroup("PrestamoGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicPrestamo logic = new LogicPrestamo(pm);
			List<Prestamo> resultado = (List<Prestamo>) pm.detachCopyAll(logic
					.getListarBeanByUsuario(idUsuario, estado));
			/*if(resultado.size()>0){
				System.out.println(resultado.get(0).getBeanCliente().getDni());
			}*/				
			  //Iterator<Prestamo> i=resultado.iterator(); 
			  //while(i.hasNext()){
			  //Prestamo c=i.next(); 
			  /*System.out.println(c.getBeanCliente().getDni()); 
			  System.out.println(c.getBeanCliente().getNombre());
			  System.out.println(c.getBeanCliente().getApellido());*/
			  //System.out.println(c.getBeanCliente().getDni());
			  //}
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

	public static List<Amortizacion> listarAmortizacionByPrestamo(String idPrestamo) throws UnknownException {

		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicAmortizacion logic = new LogicAmortizacion(pm);
			List<Amortizacion> resultado = (List<Amortizacion>) logic
					.getListarBeanByPrestamo(idPrestamo);			
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
	
	public static List<Amortizacion> listarAmortizacionByDate(String idUsuario,Date fechaIni) throws UnknownException {

		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicAmortizacion logic = new LogicAmortizacion(pm);
			List<Amortizacion> resultado = (List<Amortizacion>) logic
					.getListarBeanByDate(idUsuario,fechaIni);
			Iterator<Amortizacion> i=resultado.iterator();
			BigDecimal total=BigDecimal.ZERO;
			  while(i.hasNext()){
				  Amortizacion c=i.next(); 
			  /*System.out.println(c.getBeanCliente().getDni()); 
			  System.out.println(c.getBeanCliente().getNombre());
			  System.out.println(c.getBeanCliente().getApellido());*/
			  System.out.println(c.getBeanPrestamo().getNombre()+" "+c.getBeanPrestamo().getApellido());
			  total=total.add(BigDecimal.valueOf(c.getMonto()));
			  }
			  if(resultado.size()>0){
			  resultado.get(0).setTotalAmortizado(total.doubleValue());
			  System.out.println(resultado.get(0).getTotalAmortizado());
			  }
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
	
	public static Boolean insertarAmortizacion(Amortizacion bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I") && bean.getIdPrestamo() != null
				&& bean.getIdUsuario() != null) {
			BeanParametro parametro = new BeanParametro();
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				LogicPrestamo logicPrestamo = new LogicPrestamo(pm);
				Prestamo beanPrestamo = (Prestamo) logicPrestamo.getBean(bean.getIdPrestamo());
				BigDecimal devuelto=BigDecimal.valueOf(beanPrestamo.getDevuelto()).add(BigDecimal.valueOf(bean.getMonto()));
				BigDecimal aDevolver=BigDecimal.valueOf(beanPrestamo.getaDevolver());
				if(devuelto.compareTo(aDevolver)==0){
					beanPrestamo.setEstado("A");
				}else if(devuelto.compareTo(aDevolver)==1){
					return false;
				}
				beanPrestamo.setDevuelto(devuelto.doubleValue());
				bean.setBeanPrestamo(beanPrestamo);
				beanPrestamo.getListAmortizacion().add(bean);
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				System.out.println(beanPrestamo.getListAmortizacion().size());
				LogicPrestamo logic = new LogicPrestamo(pm);
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
	
	public static Boolean eliminarAmortizacion(Amortizacion bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") && bean.getIdPrestamo() != null
				&& bean.getIdUsuario() != null
				&& bean.getIdAmortizacion() != null) {
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(bean);
			parametro.setId(bean.getIdAmortizacion());
			parametro.setTipoOperacion(bean.getOperacion());
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				LogicPrestamo logicPrestamo = new LogicPrestamo(pm);
				Prestamo beanPrestamo = (Prestamo) logicPrestamo.getBean(bean
						.getIdPrestamo());
				//Double devuelto=beanPrestamo.getDevuelto()-bean.getMonto();
				BigDecimal devuelto=BigDecimal.valueOf(beanPrestamo.getDevuelto()).subtract(BigDecimal.valueOf(bean.getMonto()));
				if(devuelto.compareTo(BigDecimal.ZERO)==-1){
					return false;
				}
				beanPrestamo.setDevuelto(devuelto.doubleValue());
				bean.setBeanPrestamo(beanPrestamo);
				LogicPrestamo logic = new LogicPrestamo(pm);
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
}
