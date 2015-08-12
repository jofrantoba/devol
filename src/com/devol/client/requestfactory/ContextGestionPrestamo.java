package com.devol.client.requestfactory;

import java.util.Date;
import java.util.List;

import com.devol.client.beanproxy.AmortizacionProxy;
import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.server.model.process.GestionPrestamo;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = GestionPrestamo.class)
public interface ContextGestionPrestamo extends RequestContext {
	Request<Boolean> insertarPrestamo(PrestamoProxy bean);

	Request<Boolean> actualizarPrestamo(PrestamoProxy bean);

	Request<Boolean> eliminarPrestamo(PrestamoProxy bean);

	Request<List<PrestamoProxy>> listarPrestamo();

	Request<List<PrestamoProxy>> listarPrestamoByUsuario(String idUsuario,
			String estado);
	
	Request<List<AmortizacionProxy>> listarAmortizacionByPrestamo(String idPrestamo);
	
	Request<List<AmortizacionProxy>> listarAmortizacionByDate(String idUsuario,Date fechaIni);
	
	Request<Boolean> insertarAmortizacion(AmortizacionProxy bean);
	
	Request<Boolean> eliminarAmortizacion(AmortizacionProxy bean);
}
