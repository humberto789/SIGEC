package br.com.SIGEC.model;

public class Paciente extends Pessoa {
	
	private int id;

	public Paciente() {
		super();
	}

	public Paciente(int id) {
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
