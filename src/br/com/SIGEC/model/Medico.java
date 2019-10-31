package br.com.SIGEC.model;

public class Medico {
	
	private String crm;
	private int id;
	private Pessoa pessoa;
	
	public Medico() {
		pessoa = new Pessoa();
	}
	
	public Medico(String crm) {
		super();
		this.crm = crm;
		this.pessoa = new Pessoa();
	}



	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
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
