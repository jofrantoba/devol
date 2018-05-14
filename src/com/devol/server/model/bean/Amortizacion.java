package com.devol.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.devol.shared.StringHex;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.datanucleus.annotations.Unowned;

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
	@Persistent
	@Unowned
	private GestorCliente beanGestorCliente;
	@Persistent
	private String idGestorCliente;
	@Persistent
	private String rolCobrador;
	@Persistent
	private String idUsuarioCobrador;
	@Persistent
	private String nombresCobrador;
	@Persistent
	private String apellidosCobrador;
	@Persistent
	private String valueGeneratorId;
	@NotPersistent
	private String idCreateAmortizacion;

	public String getIdAmortizacion() {
		return KeyFactory.keyToString(idAmortizacion);
	}
	
	public String getIdCreateAmortizacion() {
		return idCreateAmortizacion;
	}

	public void setIdCreateAmortizacion(String idPrestamo) {
		this.idPrestamo=idPrestamo;
		Key keyPrestamo = KeyFactory.stringToKey(idPrestamo);
		valueGeneratorId=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		this.idAmortizacion = KeyFactory.createKey(keyPrestamo,
				Amortizacion.class.getSimpleName(), valueGeneratorId);
	}
	
	public void setIdAmortizacion(String idAmortizacion) {
		this.idAmortizacion=KeyFactory.stringToKey(idAmortizacion);
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

	public GestorCliente getBeanGestorCliente() {
		return beanGestorCliente;
	}

	public void setBeanGestorCliente(GestorCliente beanGestorCliente) {
		this.beanGestorCliente = beanGestorCliente;
	}

	public String getIdGestorCliente() {
		return idGestorCliente;
	}

	public void setIdGestorCliente(String idGestorCliente) {
		this.idGestorCliente = idGestorCliente;
	}

	public String getRolCobrador() {
		return rolCobrador;
	}

	public void setRolCobrador(String rolCobrador) {
		this.rolCobrador = rolCobrador;
	}

	public String getIdUsuarioCobrador() {
		return idUsuarioCobrador;
	}

	public void setIdUsuarioCobrador(String idUsuarioCobrador) {
		this.idUsuarioCobrador = idUsuarioCobrador;
	}

	public String getNombresCobrador() {
		return nombresCobrador;
	}

	public void setNombresCobrador(String nombresCobrador) {
		this.nombresCobrador = nombresCobrador;
	}

	public String getApellidosCobrador() {
		return apellidosCobrador;
	}

	public void setApellidosCobrador(String apellidosCobrador) {
		this.apellidosCobrador = apellidosCobrador;
	}
	
	
	

}
