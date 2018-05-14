package com.devol.server.control.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devol.server.model.process.GestionUsuario;

public class SrvGestionUsuario extends HttpServlet {
	private static final long serialVersionUID = 2556188587429050683L;
	private static final Logger log = Logger.getLogger(SrvGestionUsuario.class
			.getName());

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=ISO-8859-1");
		String url = request.getServletPath();
		if (url.equals("/activeaccountuser.html")) {
			activarCuentaUsuario(request, response);
		} 
	}

	

	private void activarCuentaUsuario(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String encoded = request.getParameter("encoded");
		try {
			if (GestionUsuario.activarCuenta(encoded)) {
				response.getWriter().print("La cuenta se activo satisfactoriamente");
				response.sendRedirect("https://devolpay.appspot.com");
				// request.getRequestDispatcher("http://www.webkiongo.appspot.com/#home").forward(request,
				// response);
			} else {
				response.getWriter().print("No pudimos activar su cuenta, vuelva intentarlo");
			}
		} catch (Exception ex) {
			log.warning(ex.getMessage());
			ex.printStackTrace();
			response.getWriter().print("Tuvimos un problema inesperado, vuelva a intentarlo en unos minutos");
		}
	}

	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
