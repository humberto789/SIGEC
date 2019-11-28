package br.com.SIGEC.observer;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.web.mbean.Consulta;

public class EnviarEmail implements Observador {
	
	private final String meuEmail = "sigec@gmail.com";
	private final String minhaSenha = "clinicadenatal";

	@Override
	public void notificar(Paciente paciente, Consulta consulta) {
		String destinatario = paciente.getPessoa().getEmail();
		String msg = "Sua consulta está confirmada para..." 
									+ consulta.getDataConsulta();

		SimpleEmail emailconfig = new SimpleEmail();
		emailconfig.setHostName("smtp.gmail.com");
		emailconfig.setSmtpPort(465);
		emailconfig.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
		emailconfig.setSSLOnConnect(true);

		try {
			emailconfig.setFrom(meuEmail);
			emailconfig.setSubject("SIGEC - CONFIRMAÇÃO DE CONSULTA");
			emailconfig.setMsg(msg);
			emailconfig.addTo(destinatario);
			emailconfig.send();
			System.out.println("email enviado!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
