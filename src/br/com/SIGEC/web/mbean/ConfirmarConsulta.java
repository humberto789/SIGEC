package br.com.SIGEC.web.mbean;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import br.com.SIGEC.model.EmailConsulta;
import br.com.SIGEC.model.Usuario;

public class ConfirmarConsulta {

	// Dúvidas:
	// ----> Como a mensagem vai funcionar?
	// ----> O que enviarEmail vai retornar?


	// Enviar Email
	private String enviarEmail (EmailConsulta email, Usuario user) {

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

		// Informar os dados do email
		// if (marcar = true) { // Criar a classe MarcarConsulta
		try {
			emailConfig.setFrom(meuemail);
			emailConfig.setSubject(subject);
			emailConfig.setMsg(msg);
			emailConfig.addTo(destinatario);
			emailConfig.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }

		return msg; // Precisa retornar o que? Retornar para ConfirmarPresença

	}
}

// Acessar o banco
/*
 * private final static String consultar = "select senha from consulta"; private
 * static final String URL =
 * "jdbc:mysql://localhost:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
 * private static final String USUARIO = "root"; private static final String
 * SENHA = "12345";
 */

// Recuperar dados da consulta
/*
 * public void RecuperaConsulta(MarcarConsulta consulta) {
 * 
 * Connection conexao; try { Class.forName("com.mysql.jdbc.Driver"); conexao =
 * DriverManager.getConnection(URL, USUARIO, SENHA); PreparedStatement sttmt =
 * conexao.prepareStatement(consultar); ResultSet dados = sttmt.executeQuery();
 * 
 * consulta.getDataConsulta(); consulta.getEspecialidade();
 * 
 * } catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
 * 
 * }
 */
