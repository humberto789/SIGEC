package br.com.SIGEC.model;

public class Recepcionista extends Pessoa {

	private int id;

	public Recepcionista() {
		super();
	}

	public Recepcionista(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
