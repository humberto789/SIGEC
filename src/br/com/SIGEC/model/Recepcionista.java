package br.com.SIGEC.model;

public class Recepcionista{

	private int id;
	private Pessoa pessoa;
	
	
	

	public Recepcionista() {
		this.setPessoa(new Pessoa());
	}

	public Recepcionista(int id, Pessoa pessoa) {
		super();
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
