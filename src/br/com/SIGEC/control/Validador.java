package br.com.SIGEC.control;

public class Validador {
	
	public static boolean validadorEmail(String email) {
		
		if(email != null && email.length() > 0) {
			
			String expressaoEmail = "^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$";
			
			return email.matches(expressaoEmail);
			
		}
		return false;
	}
	
	public static boolean validadorTelefone(String telefone) {
		
		if(telefone != null && telefone.length() > 0) {
			
			String telefoneComNove = ".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}";
			String telefoneSemNove = ".((10)|([1-9][1-9]).)\\\\s[2-5][0-9]{3}-[0-9]{4}";
			
			return telefone.matches(telefoneComNove) || telefone.matches(telefoneSemNove);
		
		}
		return false;
		
	}
	
	//A senha tem que ter pelo menos três desses caracteres(letra maiuscula, letra minuscula, numero ou simbolo), deve ser maior que 6 caracteres e não deve conter espaço
	public static boolean validadorSenha(String senha) {
	    if (senha.length() < 6) {
	    	
	    	boolean achouNumero = false;
	        boolean achouMaiuscula = false;
	        boolean achouMinuscula = false;
	        boolean achouSimbolo = false;
	        int contador = 0;
	    	
		    for (char c : senha.toCharArray()) {
		    	if(c == ' ') {
		    		return false;
		    		
		    	} else if (c >= '0' && c <= '9') {
		             achouNumero = true;
		             
		         } else if (c >= 'A' && c <= 'Z') {
		             achouMaiuscula = true;
		             
		         } else if (c >= 'a' && c <= 'z') {
		             achouMinuscula = true;
		             
		         } else {
		             achouSimbolo = true;
		             
		         }
		    }
		    
		    if(achouNumero = true) contador++;
		    if(achouMaiuscula = true) contador++;
		    if(achouMaiuscula = true) contador++;
		    if(achouSimbolo = true) contador++;
		    
		    if(contador >= 3) {
		    	return true;
		    	
		    }
		    return false;
	    
	    }
	    return false;
	}
}
