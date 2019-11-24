package br.com.SIGEC.model;

import java.util.Date;

public class Pessoa {

	private int id;
	private String cpf;
	private String sexo;
	private String nomeCompleto;
	private Date dataNascimento;
	private Usuario usuario;
	private Endereco endereco;
	private Telefone telefone;
	private String email;

	public Pessoa() {
		this.usuario = new Usuario();
		this.endereco = new Endereco();
		this.telefone = new Telefone();
	}

	public Pessoa(int id, String cpf, String nomeCompleto, Date dataNascimento, String sexo) {
		this.usuario = new Usuario();
		this.id = id;
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.endereco = new Endereco();
		this.telefone = new Telefone();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}
