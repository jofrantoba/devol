package com.devol.client.view.uiprestamo;

public interface InterUIPrestamo {
	void goToUIMantPrestamo(String modo);
	void cargarTabla();
	void cargarPrestamoGestorCobranza();
	void goToUIAmortizacion();
	void activarModoPrestamo();
	boolean sendPrestamoHistorial(String idPrestamo);
	void exportarData();
}
