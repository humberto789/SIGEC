package br.com.SIGEC.model;

public class Fila {
	private String senha;
	private boolean chamado;
	private Consulta consulta;
	
	public Fila() {
		consulta = new Consulta();
	}
	
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String string) {
		this.senha = string;
	}
	public boolean isChamado() {
		return chamado;
	}
	public void setChamado(boolean chamado) {
		this.chamado = chamado;
	}
	
}
