package br.com.SIGEC.model;

public class Admin {
	private int id;
	private Pessoa pessoa;

	public Admin() {
		super();
		this.pessoa = new Pessoa();
	}
	
	public Admin(int id, Pessoa pessoa) {
		this();
		this.id = id;
		this.pessoa = pessoa;
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
