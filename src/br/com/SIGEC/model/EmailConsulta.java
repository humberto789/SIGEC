package br.com.SIGEC.model;

import br.com.SIGEC.web.mbean.Consulta;

public class EmailConsulta {

	// D�vidas:
	// ----> As vari�veis podem ser declaradas assim?
	// ----> O m�todo confirmar est� certo?

	
	private String remetente = "sigec.info4m@gmail.com"; 
	private String destinatario; 
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
