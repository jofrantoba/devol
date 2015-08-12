package com.devol.server.control.rpc;

import javax.servlet.http.HttpSession;

import com.devol.client.service.ServiceGestionUsuario;
import com.devol.server.model.bean.Usuario;
import com.devol.server.model.bean.UsuarioRPC;
import com.devol.server.model.process.GestionUsuario;
import com.devol.shared.UnknownException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServiceGestionUsuarioImpl extends RemoteServiceServlet implements
		ServiceGestionUsuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UsuarioRPC logearUsuario(String correo, String clave)
			throws UnknownException {
		// TODO Auto-generated method stub		 
		Usuario bean = GestionUsuario.logearUsuario(correo, clave);		
		if (bean != null) {			
			HttpSession sesion = this.getThreadLocalRequest().getSession(true);
			String idSesion = sesion.getId();
			bean.setIdSesion(idSesion);
			sesion.setAttribute("beanusuario", bean);
			sesion.setAttribute("idsession", idSesion);
			bean.setUsuarioRPC();
		}
		return bean.getObjUsuarioRPC();
	}
	
	@Override
	public Boolean logout()
			throws UnknownException {
		// TODO Auto-generated method stub		 
        HttpSession sesion = this.getThreadLocalRequest().getSession();
        sesion.removeAttribute("beanusuario");
        sesion.removeAttribute("idsession");        
        sesion.invalidate();        
        return true;
	}
	
	

}
