package com.devol.server.model.process;

import java.util.logging.Logger;
import java.util.Date;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.devol.server.model.bean.Usuario;
import com.devol.server.model.dao.PMF;
import com.devol.server.model.logic.LogicUsuario;
import com.devol.shared.AESencrypt;
import com.devol.shared.BeanParametro;
import com.devol.shared.StringHex;
import com.devol.shared.UnknownException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class GestionUsuario {
	private static final Logger LOG = Logger.getLogger(GestionUsuario.class
			.getName());
	
	private static boolean existeCuenta(String correo,PersistenceManager pm) throws UnknownException{
		LogicUsuario logic = new LogicUsuario(pm);				
		Key id = KeyFactory.createKey(Usuario.class.getSimpleName(), correo);
		Usuario beanVerify;
		try {
			beanVerify = (Usuario) logic.getBean(id);
		} catch (UnknownException e) {
			// TODO Auto-generated catch block
			return false;
		}
		if(beanVerify!=null){
			return true;
		}else{
			return false;
		}
	}

	public static Boolean insertarUsuario(Usuario bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getCorreo() != null) {			
			BeanParametro parametro = new BeanParametro();
			//bean.setListCliente(new HashSet<Cliente>());			
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				String clave=bean.getClave();
				String encoded=AESencrypt.encrypt(clave);
				bean.setClave(encoded);
				bean.setEstadoCuenta("P");
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				if(existeCuenta(bean.getCorreo(),pm)){
					throw new UnknownException("Existe Cuenta");
				}
				LogicUsuario logic = new LogicUsuario(pm);												
				Boolean resultado1 = logic.mantenimiento(parametro);
				Boolean resultado2 = enviarMsgValidarCuenta(bean.getCorreo(),clave,bean.getNombres(),bean.getApellidos());
				if (resultado1 && resultado2) {
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

	public static Usuario logearUsuario(String correo, String clave)
			throws UnknownException {
		PersistenceManager pm = null;
		try {		
		pm = PMF.getPMF().getPersistenceManager();
		LogicUsuario logic = new LogicUsuario(pm);
		Key id = KeyFactory.createKey(Usuario.class.getSimpleName(), correo);
		Usuario bean = (Usuario) logic.getBean(id);		
		String encoded=AESencrypt.encrypt(clave);
		if (bean != null && bean.getClave().equalsIgnoreCase(encoded)) {
			return bean;
		} else {
			throw new UnknownException("Usuario o clave incorrectos");
		}
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
	
	public static Boolean enviarMsgValidarCuenta(String correo,String clave,String nombres,String apellidos) throws UnknownException{
		try {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody=buildMensajeActivarCuenta(correo,clave);
		String remitente="chescot2302@gmail.com ";
		Message msg = new MimeMessage(session);
		Multipart mp = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();		
		msg.setFrom(new InternetAddress(remitente.trim().toString(), "https://devolpay.appspot.com"));
		String usuario="Sr(a). "+nombres+" "+apellidos;
		msg.addRecipient(Message.RecipientType.TO,new InternetAddress(correo.trim().toString(), usuario));	
		msg.setSubject("Bienvenido a Devol - Active su cuenta");
		htmlPart.setContent(msgBody, "text/html");	
		mp.addBodyPart(htmlPart);
		msg.setContent(mp);
		Transport.send(msg);		
		return true;
		} catch (UnsupportedEncodingException ex) {
			// TODO Auto-generated catch block
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} catch (MessagingException ex) {
			// TODO Auto-generated catch block
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}		
	}
	
	private static String buildMensajeActivarCuenta(String correo,String clave) throws UnknownException{
		try {
		String msgBody = "<div id=\"titulo\" style=\"background-color:#0040FF; color:white; text-align: center; font-weight: bold; font-size:6mm; line-height:12mm\">DEVOL</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Devol es una herramienta que permite llevar el control de tus prestamos.</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Usuario: "+correo+"</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Contrase&ntilde;a: "+clave+"</div>";
		msgBody = msgBody+"<div style=\"padding:3mm\">Valida tu cuenta haciendo click en el siguiente enlace</div>";		
		String encrip=AESencrypt.encrypt(correo);
		String hex=StringHex.convertStringToHex(encrip);
		msgBody = msgBody+"<div style=\"padding:3mm; height:9mm; \"><a style=\"width:100%; height:100%;\"  href='https://devolpay.appspot.com/activeaccountuser.html?encoded="+hex+"'\">Validar Cuenta</a></div>";
		return msgBody;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}
	
	public static boolean activarCuenta(String codigoActivacion) throws UnknownException{
		PersistenceManager pm = null;		
		Transaction tx=null;				
			try {
			Date fecha=new Date();			
			String emailEncoded=StringHex.convertHexToString(codigoActivacion);												
			String email=AESencrypt.decrypt(emailEncoded);		
			pm = PMF.getPMF().getPersistenceManager();							
			LogicUsuario logicUsuario=new LogicUsuario(pm);			
			Usuario beanUsuario=(Usuario)logicUsuario.getBeanCorreo(email);
 			beanUsuario.setOperacion("A");																				
 			beanUsuario.setEstadoCuenta("A");
 			beanUsuario.setVersion(fecha.getTime());
			BeanParametro parametro = new BeanParametro();
			parametro.setBean(beanUsuario);
			parametro.setTipoOperacion(beanUsuario.getOperacion());			
			tx = pm.currentTransaction();
			tx.begin();			
			Boolean resultado = logicUsuario.mantenimiento(parametro);			
			if (resultado) {
				tx.commit();
				pm.close();
				return true;
			} else {
				tx.rollback();
				pm.close();
				return false;
			}			
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				LOG.warning(ex.getMessage());
				LOG.info(ex.getLocalizedMessage());
				throw new UnknownException(ex.getMessage());
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				LOG.warning(ex.getMessage());
				LOG.info(ex.getLocalizedMessage());
				throw new UnknownException(ex.getMessage());
			}finally {
				if (!pm.isClosed()) {
					if(tx!=null){
						tx.rollback();
					}
					pm.close();
				}
			}
	}


}
