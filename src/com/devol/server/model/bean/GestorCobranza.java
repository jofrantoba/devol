package com.devol.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable="true")
public class GestorCobranza implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4050448234422138484L;
	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idGestorCobranza;
	@Persistent
	@Unowned
	private Usuario beanUsuarioOwner;
	@Persistent
	private String idUsuarioOwner;	
	@Persistent
	@Unowned
	private Usuario beanUsuarioCobrador;
	@Persistent
	private String idUsuarioCobrador;	
	@Persistent
	private Date fechaInicio;
	@Persistent
	private Date fechaFin;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	
	public String getIdGestorCobranza() {
		return idGestorCobranza;
	}
	public void setIdGestorCobranza(String idGestorCobranza) {
		this.idGestorCobranza = idGestorCobranza;
	}	
	public Usuario getBeanUsuarioOwner() {
		return beanUsuarioOwner;
	}
	public void setBeanUsuarioOwner(Usuario beanUsuarioOwner) {
		this.beanUsuarioOwner = beanUsuarioOwner;
	}
	public String getIdUsuarioOwner() {
		return idUsuarioOwner;
	}
	public void setIdUsuarioOwner(String idUsuarioOwner) {
		this.idUsuarioOwner = idUsuarioOwner;
	}
	public Usuario getBeanUsuarioCobrador() {
		return beanUsuarioCobrador;
	}
	public void setBeanUsuarioCobrador(Usuario beanUsuarioCobrador) {
		this.beanUsuarioCobrador = beanUsuarioCobrador;
	}
	public String getIdUsuarioCobrador() {
		return idUsuarioCobrador;
	}
	public void setIdUsuarioCobrador(String idUsuarioCobrador) {
		this.idUsuarioCobrador = idUsuarioCobrador;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	
	
	
	
}
