package br.com.SIGEC.web.mbean;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import br.com.SIGEC.model.EmailConsulta;
import br.com.SIGEC.model.Usuario;

public class ConfirmarConsulta {

	private String enviarEmail(EmailConsulta email, Usuario user) {
		// Coletando informações
		String meuemail = email.getRemetente();
		String minhasenha = email.getSenha();
		String destinatario = user.getEmail();
		String msg = email.getMensagem();
		String subject = email.getAssunto();

		// Acessar o GMAIL
		SimpleEmail emailConfig = new SimpleEmail();
		emailConfig.setHostName("smtp.gmail.com");
		emailConfig.setSmtpPort(465);
		emailConfig.setAuthenticator(new DefaultAuthenticator(meuemail, minhasenha));
		emailConfig.setSSLOnConnect(true);

		// Enviar o email
/*		if (MarcarConsulta = true) { // Criar a classe MarcarConsulta
			try {
				emailConfig.setFrom(meuemail);
				emailConfig.setSubject(subject);
				emailConfig.setMsg(msg);
				emailConfig.addTo(destinatario);
				emailConfig.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
*/
		return msg; // Precisa retornar outra coisa

	}
}

//Enviar email

	/*
	 * public static void main(String[] args) { //Usar static tá certo? String
	 * meuemail = "sigec.info4m@gmail.com"; String minhasenha = "sigec2019"; String
	 * destinatario = "george.costa25.gc@gmail.com"; String msg = "teste";
	 * 
	 * SimpleEmail email = new SimpleEmail(); email.setHostName("smtp.gmail.com");
	 * email.setSmtpPort(465); email.setAuthenticator(new
	 * DefaultAuthenticator(meuemail, minhasenha)); email.setSSLOnConnect(true);
	 * 
	 * try { email.setFrom(meuemail);
	 * email.setSubject("SIGEC - CONFIRMAÇÃO DE CONSULTA"); email.setMsg(msg);
	 * email.addTo(destinatario); email.send();
	 * System.out.println("email enviado!");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
