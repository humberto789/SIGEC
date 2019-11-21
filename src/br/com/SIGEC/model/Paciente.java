package br.com.SIGEC.model;

public class Paciente {
	
	private int id;
	private Pessoa pessoa;
	

	public Paciente() {
		super();
		this.pessoa = new Pessoa();
	}
	
	public Paciente(int id, Pessoa pessoa) {
		this();
		this.id = id;
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
