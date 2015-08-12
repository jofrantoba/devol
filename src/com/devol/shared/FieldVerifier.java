package com.devol.shared;

public class FieldVerifier {
	public static boolean isValidDNI(String name) {
		if (isEmpty(name)) {
			return false;
		}else if(name.length()!=8){
			return false;
		}else{
			return isCadenaNumberEntero(name);
		}
	}
	
	public static boolean isEmpty(String name){
		if(name == null){
			return true;
		}else if(name.isEmpty()){
			return true;
		}else if(name.length()==0){
			return true;
		}else if(name.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isValidRUC(String name) {
		if (isEmpty(name)) {
			return false;
		}else if(name.length()!=11){
			return false;
		}else{
			return isCadenaNumberEntero(name);
		}
	}
	
	public static boolean isCadenaNumberEntero(String name){
		for(int i=0;i<name.length();i++){
			try{
			Integer.parseInt(String.valueOf(name.charAt(i)));
			}catch(Exception ex){
				return false;
			}			
		}
		return true;
	}
	
	public static boolean areStringEguals(String cadena1,String cadena2){
		if (isEmpty(cadena1)) {
			return false;
		}
		if (isEmpty(cadena2)) {
			return false;
		}
		if(cadena1.equals(cadena2)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isEmail(String name){
		if (isEmpty(name)) {
			return false;
		}else{
			int numArroba=0;
			for(int i=0;i<name.length();i++){
				if(name.charAt(i)=='@'){
					numArroba=numArroba+1;
				}
				if(numArroba>1){
					break;
				}
				if(name.charAt(i)=='!'||
				   name.charAt(i)=='"'||
				   name.charAt(i)=='#'||
				   name.charAt(i)=='$'||
				   name.charAt(i)=='%'||
				   name.charAt(i)=='&'||
				   name.charAt(i)=='/'||
				   name.charAt(i)=='('||
				   name.charAt(i)==')'||
				   name.charAt(i)=='?'||
				   //name.charAt(i)=='¿'||
				   //name.charAt(i)=='¡'||
				   name.charAt(i)=='*'||
				   name.charAt(i)=='+'||
				   name.charAt(i)=='['||
				   name.charAt(i)==']'||
				   name.charAt(i)=='}'||
				   name.charAt(i)=='{'||
				   //name.charAt(i)=='¬'||
				   name.charAt(i)=='|'||
				   //name.charAt(i)=='°'||
				   name.charAt(i)=='<'||
				   name.charAt(i)=='>'||
				   name.charAt(i)==','||
				   name.charAt(i)==';'||
				   name.charAt(i)==':'||
				   name.charAt(i)=='~'||
				   name.charAt(i)=='\\'||
				   name.charAt(i)=='`'				   
			){
			return false;	
			}
				if(name.charAt(i)=='q'||
						   name.charAt(i)=='w'||
						   name.charAt(i)=='e'||
						   name.charAt(i)=='r'||
						   name.charAt(i)=='t'||
						   name.charAt(i)=='y'||
						   name.charAt(i)=='u'||
						   name.charAt(i)=='i'||
						   name.charAt(i)=='o'||
						   name.charAt(i)=='p'||
						   name.charAt(i)=='a'||
						   name.charAt(i)=='s'||
						   name.charAt(i)=='d'||
						   name.charAt(i)=='f'||
						   name.charAt(i)=='g'||
						   name.charAt(i)=='h'||
						   name.charAt(i)=='j'||
						   name.charAt(i)=='k'||
						   name.charAt(i)=='l'||
						   //name.charAt(i)=='ñ'||
						   name.charAt(i)=='z'||
						   name.charAt(i)=='x'||
						   name.charAt(i)=='c'||
						   name.charAt(i)=='v'||
						   name.charAt(i)=='b'||
						   name.charAt(i)=='n'||
						   name.charAt(i)=='m'||
						   name.charAt(i)=='m'||
						   name.charAt(i)=='.'||
						   name.charAt(i)=='-'||
						   name.charAt(i)=='_'||
						   name.charAt(i)=='1'||
						   name.charAt(i)=='2'||
						   name.charAt(i)=='3'||
						   name.charAt(i)=='4'||
						   name.charAt(i)=='5'||
						   name.charAt(i)=='6'||
						   name.charAt(i)=='7'||
						   name.charAt(i)=='8'||
						   name.charAt(i)=='9'||
						   name.charAt(i)=='0'
					){
					continue;	
					}
			}
			if(numArroba!=1){
				return false;
			}
			return true;
		}
	}
}
