package com.devol.server.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable="true")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3751749015230499002L;
	
	@PrimaryKey
	@Persistent
	private Key idUsuario;
	@Persistent
	private String nombres;
	@Persistent
	private String apellidos;
	@Persistent
	@Unique
	private Email correo;
	@Persistent
	private String clave;
	@Persistent
	private String estadoCuenta;
	@Persistent
	private Long version;
	@Persistent(mappedBy = "beanUsuario")
	private Set<Cliente> listCliente=new HashSet<Cliente>();
	@NotPersistent
	private String operacion;
	@NotPersistent
	private String idSesion;
	@NotPersistent
	private UsuarioRPC objUsuarioRPC=new UsuarioRPC();

	public String getIdUsuario() {
		return KeyFactory.keyToString(idUsuario);
	}

	public void setIdUsuario(String correo) {
		Key key = KeyFactory.createKey(Usuario.class.getSimpleName(), correo);
		this.idUsuario = key;
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
		return correo.getEmail();
	}

	public void setCorreo(String correo) {
		this.correo = new Email(correo);
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

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public Set<Cliente> getListCliente() {
		return listCliente;
	}

	public void setListCliente(Set<Cliente> listCliente) {
		this.listCliente = listCliente;
	}
	
	public void setUsuarioRPC(){
		objUsuarioRPC.setIdUsuario(getIdUsuario());
		objUsuarioRPC.setNombres(getNombres());
		objUsuarioRPC.setApellidos(getApellidos());
		objUsuarioRPC.setCorreo(getCorreo());
		objUsuarioRPC.setVersion(getVersion());
		objUsuarioRPC.setIdSesion(getIdSesion());	
	}

	public UsuarioRPC getObjUsuarioRPC() {
		return objUsuarioRPC;
	}

	public String getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}
	
	

}
