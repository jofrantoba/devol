package com.devol.client.requestfactory;

import java.util.List;
import java.util.Set;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.beanproxy.GestorCobranzaProxy;
import com.devol.server.model.process.GestionCobranza;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = GestionCobranza.class)
public interface ContextGestionCobranza extends RequestContext{
	Request<Boolean> insertarCobrador(GestorCobranzaProxy bean);
	Request<List<GestorCobranzaProxy>> listarGestorCobranzaByUsuario(String idUsuario);
	Request<List<GestorCobranzaProxy>> listarPrestamistaByCobrador(String idUsuarioCobrador);	
	Request<Boolean> desactivarGestorCobranza(String idGestorCobranza);
	Request<Boolean> asignarClientesAlCobrador(Set<ClienteProxy> lista,String idUsuarioOwner,String idUsuarioCobrador,String idGestorCobranza);
	Request<List<ClienteProxy>> listarGestorClienteByCobrador(String idUsuarioCobrador);
	Request<List<ClienteProxy>> listarClienteSinCobrador(String idUsuarioOwner);
	Request<List<ClienteProxy>> listarGestorClienteByGestorCobranza(String idGestorCobranza);
	Request<Boolean> desactivarGestorCliente(String idGestorCliente);
}
