package com.devol.shared;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
	
	public static ArrayList<ArrayList<String>> repositorio15(HashSet<String> listCadenas){
		int contadorItems=0;
		int contadorRepositorio=2;
		int val = 15;
		ArrayList<ArrayList<String>> repositorio = new ArrayList<ArrayList<String>>();
		ArrayList<String> array = new ArrayList<String>();
		Iterator<String> iterador=listCadenas.iterator();
		System.out.println("repositorio 1");
		while(iterador.hasNext()){				
			if(val==contadorItems){
				repositorio.add(array);
				System.out.println("repositorio " + contadorRepositorio);
				array = new ArrayList<String>();
				contadorItems=0;
				contadorRepositorio=contadorRepositorio+1;
			}
			String cadena=iterador.next();	
			array.add(cadena);
			contadorItems=contadorItems+1;
		}		
		repositorio.add(array);
		return repositorio;
	}

}
