package com.devol.client.service;

import com.devol.server.model.bean.UsuarioRPC;
import com.devol.shared.UnknownException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("servicegestionusuario")
public interface ServiceGestionUsuario extends RemoteService {
	UsuarioRPC logearUsuario(String correo, String clave) throws UnknownException;

	Boolean logout() throws UnknownException;
}
