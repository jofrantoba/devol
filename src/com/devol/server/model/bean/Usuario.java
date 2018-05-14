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
	@Persistent
	private String dni;
	@Persistent
	private String telefono;
	@Persistent
	private String direccion;
	@Persistent
	private Boolean isRolOwner=false;
	@Persistent
	private Boolean isRolAdmin=false;
	@Persistent
	private Boolean isRolGestorCobranza=false;
	@Persistent
	private Set<String> roles=new HashSet<String>();
	@NotPersistent
	private String idCreateUsuario;

	public String getIdUsuario() {
		if(idUsuario!=null){
			return KeyFactory.keyToString(idUsuario);			
		}else{
			return null;
		}
		
	}
	
	public String getIdCreateUsuario() {
		return idCreateUsuario;
	}

	public void setIdCreateUsuario(String correo) {
		Key key = KeyFactory.createKey(Usuario.class.getSimpleName(), correo);
		this.idUsuario = key;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario=KeyFactory.stringToKey(idUsuario);
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
		objUsuarioRPC.setIsRolAdmin(getIsRolAdmin()==null?false:getIsRolAdmin());
		objUsuarioRPC.setIsRolOwner(getIsRolOwner()==null?false:getIsRolOwner());
		objUsuarioRPC.setIsRolGestorCobranza(getIsRolGestorCobranza()==null?false:getIsRolGestorCobranza());
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
	
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getIsRolOwner() {
		return isRolOwner;
	}

	public void setIsRolOwner(Boolean isRolOwner) {
		this.isRolOwner = isRolOwner;
	}

	public Boolean getIsRolAdmin() {
		return isRolAdmin;
	}

	public void setIsRolAdmin(Boolean isRolAdmin) {
		this.isRolAdmin = isRolAdmin;
	}

	public Boolean getIsRolGestorCobranza() {
		return isRolGestorCobranza;
	}

	public void setIsRolGestorCobranza(Boolean isRolGestorCobranza) {
		this.isRolGestorCobranza = isRolGestorCobranza;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	

}
