package com.devol.client.view.uiprestamo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devol.client.beanproxy.GestorCobranzaProxy;
import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.requestfactory.ContextGestionPrestamo;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihomecobranza.UIHomeCobranza;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.i18n.DevolConstants;
import com.devol.shared.TableToExcel;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;

public class UIPrestamoImpl extends UIPrestamo {
	private DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<PrestamoProxy> lista;
	private UIHomePrestamo uiHomePrestamo;
	private UIHomeCobranza uiHomeCobranza;	
	private GestorCobranzaProxy beanGestorCobranza;
	private boolean flagHistorial=false;

	public UIPrestamoImpl(UIHomePrestamo uiHomePrestamo) {
		this.uiHomePrestamo = uiHomePrestamo;
		// super.setHeightContainer(128);
		//try{
		//cargarTabla();
		activarModoPrestamo();
		isHomePrestamo=true;
		isHomeCobranza=false;
		//}catch(Exception ex){}
		//this.scrollPanel.refresh();	
	}
	
	public UIPrestamoImpl(UIHomeCobranza uiHomeCobranza) {
		this.uiHomeCobranza = uiHomeCobranza;
		activarModoPrestamo();
		isHomePrestamo=false;
		isHomeCobranza=true;
	}
	

	@Override
	public void goToUIMantPrestamo(String modo) {
		// TODO Auto-generated method stub
		if (modo.equalsIgnoreCase(constants.modoNuevo())) {
			uiHomePrestamo.getUIMantPrestamoImpl().setModo(modo);
			uiHomePrestamo.getUIMantPrestamoImpl().activarCampos();
			uiHomePrestamo.getUIMantPrestamoImpl().setTituloOperacion("Insertar");
			uiHomePrestamo.getContainer().showWidget(1);			
			uiHomePrestamo.getUIMantPrestamoImpl().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoEditar())) {
			PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccionPrestamo(), null);
				//Window.alert(constants.seleccionPrestamo());
				Notification not=new Notification(Notification.ALERT,constants.seleccionPrestamo());
				not.showPopup();
				return;	
			}				
			uiHomePrestamo.getUIMantPrestamoImpl().setModo(modo);
			uiHomePrestamo.getUIMantPrestamoImpl().setBean(bean);
			uiHomePrestamo.getUIMantPrestamoImpl().activarCampos();
			uiHomePrestamo.getUIMantPrestamoImpl().setTituloOperacion("Actualizar");
			uiHomePrestamo.getContainer().showWidget(1);			
			uiHomePrestamo.getUIMantPrestamoImpl().refreshScroll();
		} else if (modo.equalsIgnoreCase(constants.modoEliminar())) {
			PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();
			//Window.alert(bean.getIdCliente());
			if (bean == null){
				//Dialogs.alert(constants.alerta(), constants.seleccionPrestamo(), null);
				//Window.alert(constants.seleccionPrestamo());
				Notification not=new Notification(Notification.ALERT,constants.seleccionPrestamo());
				not.showPopup();
				return;	
			}				
			uiHomePrestamo.getUIMantPrestamoImpl().setModo(modo);
			uiHomePrestamo.getUIMantPrestamoImpl().setBean(bean);
			uiHomePrestamo.getUIMantPrestamoImpl().activarCampos();
			uiHomePrestamo.getUIMantPrestamoImpl().setTituloOperacion("Eliminar");
			uiHomePrestamo.getContainer().showWidget(1);			
			uiHomePrestamo.getUIMantPrestamoImpl().refreshScroll();			
		}

	}
	
	@Override
	public void cargarPrestamoGestorCobranza() {
		// TODO Auto-generated method stub
		popup.showPopup();
		if(beanGestorCobranza!=null){
		lista = new ArrayList<PrestamoProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();	
		Request<List<PrestamoProxy>> request = context.listarPrestamoByGestorCobranza(beanGestorCobranza.getIdGestorCobranza()).with("beanCliente");
		request.fire(new Receiver<List<PrestamoProxy>>() {
			@Override
			public void onSuccess(List<PrestamoProxy> response) {		 	
				lista.addAll(response);							
				grid.setData(lista);		
				grid.getSelectionModel().clear();
				popup.hidePopup();
			}
			
			@Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                //Window.alert(error.getMessage());
                Notification not=new Notification(Notification.WARNING,error.getMessage());
                not.showPopup();
            }
			
		});
		}
	}
		

	@Override
	public void cargarTabla() {
		popup.showPopup();
		if(UIHomeSesion.usuario!=null){
		lista = new ArrayList<PrestamoProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		String estadoPrestamo="";
		if(uiHomePrestamo.getModo().equals("HISTORIAL")){
			estadoPrestamo="A";			
		}else{
			estadoPrestamo="P";
		}
		Request<List<PrestamoProxy>> request = context.listarPrestamoByUsuario(UIHomeSesion.usuario.getIdUsuario(), estadoPrestamo).with("beanCliente");
		//Request<List<PrestamoProxy>> request = context.listarPrestamo();
		request.fire(new Receiver<List<PrestamoProxy>>() {
			@Override
			public void onSuccess(List<PrestamoProxy> response) {
				// TODO Auto-generated method stub
				/*Iterator<PrestamoProxy> i=response.iterator(); 
				  while(i.hasNext()){
					  PrestamoProxy c=i.next(); 
				  com.google.gwt.user.client.Window.alert(c.getBeanCliente().getNombre()+c.getBeanCliente().getApellido()); }*/
				lista.addAll(response);							
				grid.setData(lista);		
				grid.getSelectionModel().clear();
				popup.hidePopup();
			}
			
			@Override
            public void onFailure(ServerFailure error) {
                popup.hidePopup();
                //Window.alert(error.getMessage());
                Notification not=new Notification(Notification.WARNING,error.getMessage());
                not.showPopup();
            }
			
		});
		}
	}
	

	
	@Override
	public void goToUIAmortizacion() {
		
		// TODO Auto-generated method stub
		PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();		
		//Window.alert(bean.getIdCliente());
		if (bean == null){
			//Dialogs.alert(constants.alerta(),constants.seleccionPrestamo(), null);
			//Window.alert(constants.seleccionPrestamo());
			Notification not=new Notification(Notification.ALERT,constants.seleccionPrestamo());
			not.showPopup();
			return;	
		}			
		if(uiHomePrestamo!=null){
			if(!uiHomePrestamo.getModo().equals("HISTORIAL")){
				sendPrestamoHistorial(bean.getIdPrestamo());			
			}			
			uiHomePrestamo.getUiAmortizacionImpl().setBean(bean);
			uiHomePrestamo.getUiAmortizacionImpl().cargarTabla();
			uiHomePrestamo.getContainer().showWidget(3);
		}else if(uiHomeCobranza!=null){
			sendPrestamoHistorial(bean.getIdPrestamo());
			uiHomeCobranza.getUiAmortizacionImpl().setBean(bean);
			uiHomeCobranza.getUiAmortizacionImpl().setBeanGestorCobranza(beanGestorCobranza);
			uiHomeCobranza.getContainer().showWidget(2);
		}
	}	
	
	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		if(uiHomePrestamo!=null){
		if(uiHomePrestamo.getModo().equals("HISTORIAL")){
			uiHomePrestamo.getHeader().getLblTitulo().setText(constants.historial());
			toolBar.getBtnNuevo().setVisible(true);			
		}else{
			toolBar.getBtnNuevo().setVisible(true);
		}
		}else if(uiHomeCobranza!=null){
			uiHomeCobranza.getHeader().getLblTitulo().setText(constants.prestamos());
			toolBar.getBtnNuevo().setVisible(false);
			toolBar.getBtnEditar().setVisible(false);
			toolBar.getBtnEliminar().setVisible(false);
		}
	}
	
	@Override
	public boolean sendPrestamoHistorial(String idPrestamo) {
		// TODO Auto-generated method stub		
		flagHistorial=false;
		FACTORY.initialize(EVENTBUS);
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();
		Request<Boolean> request =context.sendPrestamoHistorial(idPrestamo);		
		request.fire(new Receiver<Boolean>() {					

			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if(response){
					Notification not=new Notification(Notification.INFORMATION,constants.prestamoEnviadoHistoria());
					not.showPopup();
					flagHistorial=response;					
				}
			}});
		return flagHistorial;
	}

	public void setBeanGestorCobranza(GestorCobranzaProxy beanGestorCobranza) {
		this.beanGestorCobranza = beanGestorCobranza;
	}
	
	@Override
	public void exportarData() {
		// TODO Auto-generated method stub
		Date date=new Date();
		String nameFile="prestamo-"+date.getTime()+".xls";       	 
		int row=0;
        List<PrestamoProxy> lista;
        if(!grid.getDataProvider().hasFilter()){
            row=grid.getData().size();
            lista=grid.getData();
        }else{
            row=grid.getDataProvider().resulted.size();  
            lista=grid.getDataProvider().resulted;
        }
        if(row==0){
            Notification not=new Notification(Notification.ALERT,"Grid sin datos");
            not.showPopup();
            return;
        }
        FlexTable flex=new FlexTable();           
        flex.setText(0, 0, "TP");
        flex.setText(0, 1, "FECHA");         
        flex.setText(0, 2, "DNI");    
        flex.setText(0, 3, "CLIENTE");
        flex.setText(0, 4, "MONTO");           
        flex.setText(0, 5, "TASA");           
        flex.setText(0, 6, "A_DEVOLVER");           
        flex.setText(0, 7, "DEVUELTO");                                                  
        flex.setText(0, 8, "GLOSA");        
        flex.setText(0, 9, "ESTADO");
        for(int j=0;j<10;j++){
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontWeight(Style.FontWeight.BOLD);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setFontSize(14, Style.Unit.PX);
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setBackgroundColor("#0170C9");            
            flex.getFlexCellFormatter().getElement(0, j).getStyle().setColor("#fff");
        }        
        int fila=1;
        for(int i=0;i<row;i++){           
        	PrestamoProxy bean=lista.get(i);                       
            flex.setText(fila, 0, bean.getTipoPrestamo());
            flex.setText(fila, 1, fmt.format(bean.getFecha()));
            flex.setText(fila, 2, bean.getDni());
            flex.setText(fila, 3, bean.getNombre()+" "+bean.getApellido());
            flex.setText(fila, 4, bean.getMonto().toString());
            flex.setText(fila, 5, bean.getTasa().toString());
            flex.setText(fila, 6, bean.getaDevolver().toString());
            flex.setText(fila, 7, bean.getDevuelto().toString());
            flex.setText(fila, 8, bean.getGlosa());
            flex.setText(fila, 9, bean.getEstado());
            fila=fila+1;
        }               
		TableToExcel.save(flex,nameFile);
	}
	
}
