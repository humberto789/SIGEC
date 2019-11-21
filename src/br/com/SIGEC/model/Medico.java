package br.com.SIGEC.model;

public class Medico {

	private int id;
	private Pessoa pessoa;
	private String crm;
	private String especialidade;

	public Medico() {
		this.pessoa = new Pessoa();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public Medico(String crm) {
		super();
		this.crm = crm;
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setPessoa(Pessoa pessoa) {

	}
	
}
