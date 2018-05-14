package com.devol.client.view.uimantprestamo;

import com.devol.client.beanproxy.UsuarioProxy;

public interface InterUIMantPrestamo {
	void goToUIPrestamo();

	void registrar();
	
	void goToUIClienteAdd();
	
	boolean isValidData();
	
	void activarModoPrestamo();	
		
}
