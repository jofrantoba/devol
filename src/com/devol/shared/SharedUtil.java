package com.devol.shared;

public class SharedUtil {
	
	public static String firstLetterMayus(String cadena){
		if(!cadena.isEmpty()){
			String firstLetter=cadena.substring(0, 1).toUpperCase();
			if(cadena.length()>1){
				cadena=firstLetter+cadena.substring(1,cadena.length()).toLowerCase();
			}else{
				cadena=firstLetter;
			}					
		}
		return cadena;
	}

}
