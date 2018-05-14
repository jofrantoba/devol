package com.devol.client.beanproxy;

import java.util.Date;

import com.devol.server.locator.LocatorGestorCobranza;
import com.devol.server.model.bean.GestorCobranza;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = GestorCobranza.class, locator = LocatorGestorCobranza.class)
public interface GestorCobranzaProxy extends EntityProxy {
	public String getIdGestorCobranza();
	
	public void setIdGestorCobranza(String idGestorCobranza);
	
	public UsuarioProxy getBeanUsuarioOwner();
	
	public void setBeanUsuarioOwner(UsuarioProxy beanUsuarioOwner);
	
	public String getIdUsuarioOwner();
	
	public void setIdUsuarioOwner(String idUsuarioOwner);
	
	public UsuarioProxy getBeanUsuarioCobrador();
	
	public void setBeanUsuarioCobrador(UsuarioProxy beanUsuarioCobrador);
	
	public String getIdUsuarioCobrador();
	
	public void setIdUsuarioCobrador(String idUsuarioCobrador);
	
	public Date getFechaInicio();
	
	public void setFechaInicio(Date fechaInicio);
	
	public Date getFechaFin();
	
	public void setFechaFin(Date fechaFin);
	
	public String getEstado();
	
	public void setEstado(String estado);
	
	public Long getVersion();
	
	public void setVersion(Long version);
	
	public String getOperacion();
	
	public void setOperacion(String operacion);
}
