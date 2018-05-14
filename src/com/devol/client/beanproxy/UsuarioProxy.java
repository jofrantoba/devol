package com.devol.client.beanproxy;

import java.util.Set;

import com.devol.server.locator.LocatorUsuario;
import com.devol.server.model.bean.Usuario;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Usuario.class, locator = LocatorUsuario.class)
public interface UsuarioProxy extends EntityProxy {

	public String getIdUsuario();

	public void setIdCreateUsuario(String correo);
	
	public String getIdCreateUsuario();
	
	public void setIdUsuario(String idUsuario);

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
	
	public String getDni();

	public void setDni(String dni);

	public Set<String> getRoles();

	public void setRoles(Set<String> roles);

	public String getTelefono();

	public void setTelefono(String telefono);

	public Boolean getIsRolOwner();

	public void setIsRolOwner(Boolean isRolOwner);

	public Boolean getIsRolAdmin();

	public void setIsRolAdmin(Boolean isRolAdmin);

	public Boolean getIsRolGestorCobranza();

	public void setIsRolGestorCobranza(Boolean isRolGestorCobranza);

	public String getDireccion();

	public void setDireccion(String direccion);
	
}
