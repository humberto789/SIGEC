package br.com.SIGEC.model;

import java.util.ArrayList;

public class Fila {
	private String senha;
	private ArrayList<String> lista; //N�o est� sendo �til por enquanto
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String string) {
		this.senha = string;
	}
	public ArrayList<String> getLista() {
		return lista;
	}
	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}
	
}
