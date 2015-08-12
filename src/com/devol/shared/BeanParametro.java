package com.devol.shared;



import com.google.gwt.user.client.rpc.IsSerializable;

public class BeanParametro implements IsSerializable{
	
	private Object id;
	private Object bean;
	private String tipoOperacion;
	
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

}
