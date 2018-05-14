package com.devol.client.resource;

import com.devol.client.resource.cssresource.FormularioCss;
import com.devol.client.resource.cssresource.ModelCss;
import com.devol.client.resource.cssresource.NotificationCss;
import com.devol.client.resource.cssresource.PanelFlowCss;
import com.devol.client.resource.cssresource.UIAmortizacionCss;
import com.devol.client.resource.cssresource.UIClienteCss;
import com.devol.client.resource.cssresource.UIHomeCss;
import com.devol.client.resource.cssresource.UIMantAmortizacionCss;
import com.devol.client.resource.cssresource.UIMenuCss;
import com.devol.client.resource.cssresource.UIPrestamoCss;
import com.devol.client.resource.cssresource.UIRecaudadoCss;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MyResource extends ClientBundle {

	public static final MyResource INSTANCE = GWT.create(MyResource.class);
	
	@Source("style/Notification.css")
	NotificationCss getStlNotification();

	@Source("style/panelflow.css")
	PanelFlowCss getStlPanelFlow();

	@Source("style/formulario.css")
	FormularioCss getStlFormulario();

	@Source("style/uimenu.css")
	UIMenuCss getStlUIMenu();

	@Source("style/uihome.css")
	UIHomeCss getStlUIHome();

	@Source("style/model.css")
	ModelCss getStlModel();

	@Source("style/uicliente.css")
	UIClienteCss getStlUICliente();

	@Source("style/uiprestamo.css")
	UIPrestamoCss getStlUIPrestamo();

	@Source("style/uirecaudado.css")
	UIRecaudadoCss getStlUIRecaudado();

	@Source("style/uiamortizacion.css")
	UIAmortizacionCss getStlUIAmortizacion();

	@Source("style/uimantamortizar.css")
	UIMantAmortizacionCss getStlUIMantAmortizar();

	@Source("image/check_pago32.png")
	ImageResource getImgCheckPago32();

	@Source("image/clientes32.png")
	ImageResource getImgClientes32();

	@Source("image/off32.png")
	ImageResource getImgOff32();

	@Source("image/pago32.png")
	ImageResource getImgPago32();

	@Source("image/back32.png")
	ImageResource getImgBack32();

	@Source("image/menu32.png")
	ImageResource getImgMenu32();

	@Source("image/new32.png")
	ImageResource getImgNew32();

	@Source("image/edit32.png")
	ImageResource getImgEdit32();

	@Source("image/remove32.png")
	ImageResource getImgRemove32();

	@Source("image/refresh32.png")
	ImageResource getImgRefresh32();

	@Source("image/calendar32.png")
	ImageResource getImgCalendar32();

	@Source("image/clieAdd32.png")
	ImageResource getImgClieAdd32();

	@Source("image/up32.png")
	ImageResource getImgUp32();

	@Source("image/down32.png")
	ImageResource getImgDown32();

	@Source("image/select32.png")
	ImageResource getImgSelect32();

	@Source("image/cash32.png")
	ImageResource getImgCash32();

	@Source("image/reporte32.png")
	ImageResource getImgRepote32();

	@Source("image/filtro32.png")
	ImageResource getImgFiltro32();

	@Source("image/about32.png")
	ImageResource getImgAbout32();

	@Source("image/mundo32.png")
	ImageResource getImgMundo32();

	@Source("image/wallpaper.jpg")
	ImageResource getWallpaper();

	@Source("image/alert32.png")
	ImageResource getImgAlert32();

	@Source("image/warning32.png")
	ImageResource getImgWarning32();

	@Source("image/information32.png")
	ImageResource getImgInformation32();
	
	@Source("image/cobrador32.png")
	ImageResource getImgCobrador32();
	
	@Source("image/cobranza32.png")
	ImageResource getImgCobranza32();
	
	@Source("image/desactivar32.png")
	ImageResource getImgDesactivar32();
	
	@Source("image/groupclientes32.png")
	ImageResource getImgGroupClientes32();

}
