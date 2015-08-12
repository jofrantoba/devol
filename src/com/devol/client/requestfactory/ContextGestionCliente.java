package com.devol.client.requestfactory;

import java.util.List;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.server.model.process.GestionCliente;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = GestionCliente.class)
public interface ContextGestionCliente extends RequestContext {
	Request<Boolean> insertarCliente(ClienteProxy bean);

	Request<Boolean> actualizarCliente(ClienteProxy bean);

	Request<Boolean> eliminarCliente(ClienteProxy bean);

	Request<List<ClienteProxy>> listarCliente();
	
	Request<List<ClienteProxy>> listarClienteByUsuario(String idUsuario);
}
