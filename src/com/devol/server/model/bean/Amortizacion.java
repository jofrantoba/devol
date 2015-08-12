package com.devol.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable="true")
public class Amortizacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	private Key idAmortizacion;
	@Persistent
	private Double monto;
	@Persistent
	private Date fecha;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Prestamo beanPrestamo;
	@Persistent
	private String idPrestamo;
	@Persistent
	private String idUsuario;
	@NotPersistent
	private String codAmortizacion;
	@NotPersistent
	private Double totalAmortizado;

	public String getIdAmortizacion() {
		return KeyFactory.keyToString(idAmortizacion);
	}

	public void setIdAmortizacion(String idPrestamo) {
		this.idPrestamo=idPrestamo;
		Key keyPrestamo = KeyFactory.stringToKey(idPrestamo);
		this.idAmortizacion = KeyFactory.createKey(keyPrestamo,
				Amortizacion.class.getSimpleName(), java.util.UUID.randomUUID()
						.toString());
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Prestamo getBeanPrestamo() {
		return beanPrestamo;
	}

	public void setBeanPrestamo(Prestamo beanPrestamo) {
		this.beanPrestamo = beanPrestamo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAmortizacion == null) ? 0 : idAmortizacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Amortizacion other = (Amortizacion) obj;
		if (idAmortizacion == null) {
			if (other.idAmortizacion != null)
				return false;
		} else if (!idAmortizacion.equals(other.idAmortizacion))
			return false;
		return true;
	}

	public String getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(String idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCodAmortizacion() {
		return codAmortizacion;
	}

	public void setCodAmortizacion(String codAmortizacion) {
		this.idAmortizacion=KeyFactory.stringToKey(codAmortizacion);
		this.codAmortizacion = codAmortizacion;
	}

	public Double getTotalAmortizado() {
		return totalAmortizado;
	}

	public void setTotalAmortizado(Double totalAmortizado) {
		this.totalAmortizado = totalAmortizado;
	}
	
	
	

}
