package br.com.SIGEC.model;

import br.com.SIGEC.web.mbean.Consulta;

public class EmailConsulta {

	// Dúvidas:
	// ----> As variáveis podem ser declaradas assim?
	// ----> O método confirmar está certo?

	
	private String remetente = "sigec.info4m@gmail.com"; 
	private String destinatario; 
	private String senha = "sigec2019"; 
	private String assunto = "Confirmação de Consulta - SIGEC";
	private Consulta data;
	private String mensagem = "Vc tem uma consulta no dia " + data.getDataConsulta() + "na clinica XXYY, no endereço Rua Zé Ninguém"; 


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
