package br.com.SIGEC.model;

public class Telefone {
	
	private int numero;
	private int id;
	
	public Telefone() {
		super();
	}

	public Telefone(int numero, int id) {
		super();
		this.numero = numero;
		this.id = id;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
