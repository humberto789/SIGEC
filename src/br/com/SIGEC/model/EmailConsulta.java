package br.com.SIGEC.model;

import br.com.SIGEC.web.mbean.Consulta;

public class EmailConsulta {
	Usuario user = new Usuario();

	private String remetente = "sigec.info4m@gmail.com"; 
	private String destinatario = user.getEmail(); 
	private String senha = "sigec2019"; 
	private String assunto = "Confirma��o de Consulta - SIGEC";
	private Consulta data;
	private String mensagem = "Vc tem uma consulta no dia " + data.getDataConsulta() + "na clinica XXYY, no endere�o Rua Z� Ningu�m"; 


	public String getRemetente() {
		return remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getSenha() {
		return senha;
	}

	

}
