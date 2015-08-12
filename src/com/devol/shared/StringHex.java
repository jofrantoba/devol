package com.devol.shared;

public class StringHex {
	
	public static String convertStringToHex(String str){
		 
		  char[] chars = str.toCharArray();
	 
		  StringBuffer hex = new StringBuffer();
		  for(int i = 0; i < chars.length; i++){
		    hex.append(Integer.toHexString((int)chars[i]));
		  }
	 
		  return hex.toString();
	  }
	 
	  public static String convertHexToString(String hex){
	 
		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
	 
		  for( int i=0; i<hex.length()-1; i+=2 ){
	 
		      String output = hex.substring(i, (i + 2));	
		      int decimal = Integer.parseInt(output, 16);
		      sb.append((char)decimal);
	 
		      temp.append(decimal);
		  }		  	 
		  return sb.toString();
	  }
		
}
