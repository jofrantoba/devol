package com.devol.client.beanproxy;

import java.util.Set;

import com.devol.server.locator.LocatorUsuario;
import com.devol.server.model.bean.Usuario;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Usuario.class, locator = LocatorUsuario.class)
public interface UsuarioProxy extends EntityProxy {

	public String getIdUsuario();

	public void setIdUsuario(String correo);

	public String getNombres();

	public void setNombres(String nombres);

	public String getApellidos();

	public void setApellidos(String apellidos);

	public String getCorreo();

	public void setCorreo(String correo);

	public String getClave();

	public void setClave(String clave);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public String getIdSesion();

	public void setIdSesion(String idSesion);

	public Set<ClienteProxy> getListCliente();

	public void setListCliente(Set<ClienteProxy> listCliente);
	
	public String getEstadoCuenta();

	public void setEstadoCuenta(String estadoCuenta);
}
