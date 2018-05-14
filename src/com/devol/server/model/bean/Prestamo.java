package com.devol.server.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.devol.shared.StringHex;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable="true")
public class Prestamo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent
	private Key idPrestamo;
	@Persistent
	private Date fecha;
	@Persistent
	private Double monto;
	@Persistent
	private Double tasa;
	@Persistent
	private Double aDevolver;
	@Persistent
	private Double devuelto;
	@Persistent
	private String estado;
	@Persistent
	private String dni;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private Cliente beanCliente;
	@Persistent(mappedBy = "beanPrestamo")
	@Element(dependent = "true")
	private Set<Amortizacion> listAmortizacion=new HashSet<Amortizacion>();
	@Persistent
	private String idCliente;
	@Persistent
	private String idUsuario;
	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	@NotPersistent
	private String codPrestamo;
	@Persistent
	private String valueGeneratorId;
	@NotPersistent
	private String idCreatePrestamo;
	

	public String getIdPrestamo() {
		return KeyFactory.keyToString(idPrestamo);
	}
	
	public String getIdCreatePrestamo() {
		return idCreatePrestamo;
	}
	
	public void setIdCreatePrestamo(String idCliente) {
		this.idCliente = idCliente;
		Key keyCliente = KeyFactory.stringToKey(idCliente);
		valueGeneratorId=StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
		this.idPrestamo = KeyFactory.createKey(keyCliente, Prestamo.class
				.getSimpleName(), valueGeneratorId);
	}
	
	public void setIdPrestamo(String idPrestamo) {
		this.idPrestamo=KeyFactory.stringToKey(idPrestamo);
	}
	
	 

	public String getCodPrestamo() {
		return codPrestamo;
	}

	public void setCodPrestamo(String codPrestamo) {		
			this.idPrestamo=KeyFactory.stringToKey(codPrestamo);
			this.codPrestamo = codPrestamo;		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public Double getaDevolver() {
		return aDevolver;
	}

	public void setaDevolver(Double aDevolver) {
		this.aDevolver = aDevolver;
	}

	public Double getDevuelto() {
		return devuelto;
	}

	public void setDevuelto(Double devuelto) {
		this.devuelto = devuelto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {				
		this.dni = dni;
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

	public Cliente getBeanCliente() {
		return beanCliente;
	}

	public void setBeanCliente(Cliente beanCliente) {
		this.beanCliente = beanCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPrestamo == null) ? 0 : idPrestamo.hashCode());
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
		Prestamo other = (Prestamo) obj;
		if (idPrestamo == null) {
			if (other.idPrestamo != null)
				return false;
		} else if (!idPrestamo.equals(other.idPrestamo))
			return false;
		return true;
	}

	public Set<Amortizacion> getListAmortizacion() {
		return listAmortizacion;
	}

	public void setListAmortizacion(Set<Amortizacion> listAmortizacion) {
		this.listAmortizacion = listAmortizacion;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}		
	
	

}
