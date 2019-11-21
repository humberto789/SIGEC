package br.com.SIGEC.model;

public class EmailConsulta {
	private String remetente = "sigec.info4m@gmail.com"; //Fixo - Email da Clinica
	private String destinatario; //email do paciente
	private String senha  = "sigec2019"; //Fixo - senha do email da clínica
	private String assunto = "Confirmação de Consulta - SIGEC"; //Fixo
	private String dataConsulta = "20.16.1820"; // mudar quando criar a classe Marcarconsulta
	private String enderecoClinica = "20.16.1820"; // mudar quando criar a classe Clinica
	private String mensagem = "Vc tem uma consulta no dia " + dataConsulta + "na clinica XXYY, no endereço " + enderecoClinica; //Fixo
	
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
