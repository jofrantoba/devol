package com.devol.client.requestfactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface FactoryGestion extends RequestFactory {
	ContextGestionCliente contextGestionCliente();

	ContextGestionUsuario contextGestionUsuario();

	ContextGestionPrestamo contextGestionPrestamo();
}