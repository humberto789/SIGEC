package br.com.SIGEC.model;

public class Usuario {
	
	private String senha;
	private String email;
	private String login;
	private boolean ativo;
	private int id;
	private String tipoUsuario;
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario() {
		this.ativo = false;
	}
	
	public Usuario(String senha, String email, String login, int id) {
		super();
		this.senha = senha;
		this.email = email;
		this.login = login;
		this.ativo = false;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
