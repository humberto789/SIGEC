package br.com.SIGEC.model;

public class Telefone {
	
	private int numero;
	private int ddd;
	private int id;
	
	public Telefone() {
		super();
	}

	public Telefone(int numero, int ddd, int id) {
		super();
		this.numero = numero;
		this.ddd = ddd;
		this.id = id;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
