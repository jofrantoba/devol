package com.devol.client.beanproxy;

import java.util.Date;
import java.util.Set;

import com.devol.server.locator.LocatorPrestamo;
import com.devol.server.model.bean.Prestamo;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Prestamo.class, locator = LocatorPrestamo.class)
public interface PrestamoProxy extends EntityProxy {
	public String getIdPrestamo();

	public void setIdPrestamo(String idCliente);

	public String getCodPrestamo();

	public void setCodPrestamo(String codPrestamo);

	public Date getFecha();

	public void setFecha(Date fecha);

	public Double getMonto();

	public void setMonto(Double monto);

	public Double getTasa();

	public void setTasa(Double tasa);

	public Double getaDevolver();

	public void setaDevolver(Double aDevolver);

	public Double getDevuelto();

	public void setDevuelto(Double devuelto);

	public String getEstado();

	public void setEstado(String estado);

	public String getDni();

	public void setDni(String dni);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public ClienteProxy getBeanCliente();

	public void setBeanCliente(ClienteProxy beanCliente);

	public Set<AmortizacionProxy> getListAmortizacion();

	public void setListAmortizacion(Set<AmortizacionProxy> listAmortizacion);

	public String getIdCliente();

	public String getIdUsuario();

	public void setIdUsuario(String idUsuario);
	
	public void setIdCliente(String idCliente);
	
	public String getNombre();

	public void setNombre(String nombre);

	public String getApellido();

	public void setApellido(String apellido);
}
