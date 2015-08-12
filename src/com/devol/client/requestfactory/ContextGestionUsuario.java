package com.devol.client.requestfactory;

import com.devol.client.beanproxy.UsuarioProxy;
import com.devol.server.model.process.GestionUsuario;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = GestionUsuario.class)
public interface ContextGestionUsuario extends RequestContext {
	Request<Boolean> insertarUsuario(UsuarioProxy bean);
}
