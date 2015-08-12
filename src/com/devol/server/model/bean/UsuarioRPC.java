package com.devol.server.model.bean;

import java.io.Serializable;

public class UsuarioRPC implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idUsuario;	
	private String nombres;	
	private String apellidos;	
	private String correo;	
	private String clave;	
	private Long version;				
	private String idSesion;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}	
	public String getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
	
	
		
}
