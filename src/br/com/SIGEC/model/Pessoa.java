package br.com.SIGEC.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pessoa {
	
	private int id;
	private String cpf;
	private String sexo;
	private String nomeCompleto;
	private Date dataNascimento;
	private Usuario usuario;
	private List<Endereco> enderecos;
	private List<Telefone> telefones;
	
	public Pessoa() {
		this.usuario = new Usuario();
		this.enderecos = new ArrayList<Endereco>();
		this.telefones = new ArrayList<Telefone>();
	}
	
	
	
	public Pessoa(int id, String cpf, String nomeCompleto, Date dataNascimento, String sexo) {
		this.usuario = new Usuario();
		this.enderecos = new ArrayList<Endereco>();
		this.telefones = new ArrayList<Telefone>();
		this.id = id;
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}
