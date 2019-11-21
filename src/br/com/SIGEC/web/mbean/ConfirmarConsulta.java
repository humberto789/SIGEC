package br.com.SIGEC.web.mbean;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.SimpleEmail;
//import br.com.SIGEC.model.EmailConsulta;
//import br.com.SIGEC.model.Usuario;

public class ConfirmarConsulta {

	public void enviarEmail() {
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
