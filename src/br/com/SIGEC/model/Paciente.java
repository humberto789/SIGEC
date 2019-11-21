package br.com.SIGEC.model;

public class Paciente {

	private Pessoa pessoa;
	private int id;

	public Paciente() {
		super();
		setPessoa(new Pessoa());
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
