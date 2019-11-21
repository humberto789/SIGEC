package br.com.SIGEC.web.mbean;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import br.com.SIGEC.model.EmailConsulta;
import br.com.SIGEC.model.Usuario;

public class ConfirmarConsulta {

	// Necessário:
	// ----> criar as classes: MarcarConsulta; clinica

	// Dúvidas:
	// ----> Como a mensagem vai funcionar?
	// ----> O que enviarEmail vai retornar?

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
	//	if (MarcarConsulta = true) { // Criar a classe MarcarConsulta
			try {
				emailConfig.setFrom(meuemail);
				emailConfig.setSubject(subject);
				emailConfig.setMsg(msg);
				emailConfig.addTo(destinatario);
				emailConfig.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}

		return msg; // Precisa retornar o que? Retornar para ConfirmarPresença

	}
}

