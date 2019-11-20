package br.com.SIGEC.model;

public class ConfirmarConsulta {

	//import org.apache.commons.mail.DefaultAuthenticator;
	//import org.apache.commons.mail.SimpleEmail;

	private String remetente;
	private String destinatario;
	private String assunto;
	private String msg;

	public ConfirmarConsulta(String remetente, String destinatario, String assunto, String msg) {
		super();
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.msg = msg;
	}
	private String getRemetente() {
		return remetente;
	}
	private void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	private String getDestinatario() {
		return destinatario;
	}
	private void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	private String getAssunto() {
		return assunto;
	}
	private void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	private String getMsg() {
		return msg;
	}
	private void setMsg(String msg) {
		this.msg = msg;
	}

	public void enviarEmail() {
		
	}

		//Enviar email
		
		/*	public static void main(String[] args) { //Usar static tá certo?
			String meuemail = "sigec.info4m@gmail.com";
			String minhasenha = "sigec2019";
			String destinatario = "george.costa25.gc@gmail.com";
			String msg = "teste";

			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(meuemail, minhasenha));
			email.setSSLOnConnect(true);

			try {
				email.setFrom(meuemail);
				email.setSubject("SIGEC - CONFIRMAÇÃO DE CONSULTA");
				email.setMsg(msg);
				email.addTo(destinatario);
				email.send();
				System.out.println("email enviado!");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} */

	}