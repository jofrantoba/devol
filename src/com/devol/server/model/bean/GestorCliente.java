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
public class GestorCliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idGestorCliente;
	@Persistent
	@Unowned
	private Usuario beanUsuarioOwner;
	@Persistent
	private String idUsuarioOwner;
	@Persistent
	@Unowned
	private GestorCobranza beanGestorCobranza;
	@Persistent
	private String idGestorCobranza;
	@Persistent
	@Unowned
	private Usuario beanUsuarioCobrador;
	@Persistent
	private String idUsuarioCobrador;
	@Persistent
	@Unowned
	private Cliente beanCliente;
	@Persistent
	private String idCliente;
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
	
	public String getIdGestorCliente() {
		return idGestorCliente;
	}
	public void setIdGestorCliente(String idGestorCliente) {
		this.idGestorCliente = idGestorCliente;
	}	
	public GestorCobranza getBeanGestorCobranza() {
		return beanGestorCobranza;
	}
	public void setBeanGestorCobranza(GestorCobranza beanGestorCobranza) {
		this.beanGestorCobranza = beanGestorCobranza;
	}
	public String getIdGestorCobranza() {
		return idGestorCobranza;
	}
	public void setIdGestorCobranza(String idGestorCobranza) {
		this.idGestorCobranza = idGestorCobranza;
	}
	public Cliente getBeanCliente() {
		return beanCliente;
	}
	public void setBeanCliente(Cliente beanCliente) {
		this.beanCliente = beanCliente;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
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
	
		
}
