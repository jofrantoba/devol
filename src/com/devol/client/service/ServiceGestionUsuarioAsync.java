package com.devol.client.service;

import com.devol.server.model.bean.UsuarioRPC;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceGestionUsuarioAsync {
	void logearUsuario(String correo, String clave,
			AsyncCallback<UsuarioRPC> callback);

	void logout(AsyncCallback<Boolean> callback);
}
