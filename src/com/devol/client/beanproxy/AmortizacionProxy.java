package com.devol.client.beanproxy;

import java.util.Date;

import com.devol.server.locator.LocatorAmortizacion;
import com.devol.server.model.bean.Amortizacion;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Amortizacion.class, locator = LocatorAmortizacion.class)
public interface AmortizacionProxy extends EntityProxy {
	public String getIdAmortizacion();

	public void setIdAmortizacion(String idPrestamo);

	public Double getMonto();

	public void setMonto(Double monto);

	public Date getFecha();

	public void setFecha(Date fecha);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public PrestamoProxy getBeanPrestamo();

	public void setBeanPrestamo(PrestamoProxy beanPrestamo);
	
	public String getIdPrestamo();

	public void setIdPrestamo(String idPrestamo);

	public String getIdUsuario();

	public void setIdUsuario(String idUsuario);
	
	public String getCodAmortizacion();

	public void setCodAmortizacion(String codAmortizacion);
	
	public Double getTotalAmortizado();

	public void setTotalAmortizado(Double totalAmortizado);

}
