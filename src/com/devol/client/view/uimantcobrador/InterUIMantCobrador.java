package com.devol.client.view.uimantcobrador;

import com.devol.client.beanproxy.UsuarioProxy;

public interface InterUIMantCobrador {
	
	void goToUICobrador();

	void registrar();
	
	boolean isValidData();
	
	void getCobrador();
	
	void loadFieldsCobrador();
	
}
