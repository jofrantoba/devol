package com.devol.client.beanproxy;

import java.util.Set;

import com.devol.server.locator.LocatorCliente;
import com.devol.server.model.bean.Cliente;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Cliente.class, locator = LocatorCliente.class)
public interface ClienteProxy extends EntityProxy {
	public String getIdCliente();

	public void setIdCliente(String idUsuario);	

	public String getDni();

	public void setDni(String dni);

	public String getNombre();

	public void setNombre(String nombre);

	public String getApellido();

	public void setApellido(String apellido);

	public String getTelefono();

	public void setTelefono(String telefono);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public String getDireccion();

	public void setDireccion(String direccion);

	public String getIdUsuario();
	
	public void setIdUsuario(String idUsuario);

	public UsuarioProxy getBeanUsuario();

	public void setBeanUsuario(UsuarioProxy beanUsuario);

	public Set<PrestamoProxy> getListPrestamo();
	 
	public void setListPrestamo(Set<PrestamoProxy> listPrestamo);
	
	public String getCodCliente();

	public void setCodCliente(String codCliente);
	
	public Integer getNumPrestamo();

	public void setNumPrestamo(Integer numPrestamo);

}
