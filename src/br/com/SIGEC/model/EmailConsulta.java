package br.com.SIGEC.model;

public class EmailConsulta {

	// D�vidas:
	// ----> As vari�veis podem ser declaradas assim?
	// ----> O m�todo confirmar est� certo?

	private String remetente = "sigec.info4m@gmail.com"; // Fixo - Email da Clinica
	private String destinatario; // email do paciente
	private String senha = "sigec2019"; // Fixo - senha do email da cl�nica
	private String assunto = "Confirma��o de Consulta - SIGEC"; // Fixo - Assunto do email
	private String dataConsulta = "20.16.1820"; // mudar quando criar a classe Marcarconsulta
	private String mensagem = "Vc tem uma consulta no dia " + dataConsulta
			+ "na clinica XXYY, no endere�o Rua Z� Ningu�m"; // Fixo - corpo da mensagem

	// Get and Sets
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

	// m�todos?
	public void confirmar() {
		String resposta = null;
		if (resposta.equalsIgnoreCase("Sim")) {
			// Faz alguma coisa

		} else if (resposta.equalsIgnoreCase("N�o")) {
			// Faz alguma coisa

		}
	}

}
