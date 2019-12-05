package br.com.SIGEC.web.mbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import br.com.SIGEC.control.ConsultaDAO;
import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.model.EmailConsulta;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Usuario;
import br.com.SIGEC.model.Consulta;
import br.com.SIGEC.observer.EnviarEmail;
import br.com.SIGEC.observer.Observador;

@ManagedBean
@RequestScoped
public class ConfirmarConsultaMB extends AbstractMBean {

	/*
	 * NECESSÁRIO INSERIR O QUARTZ; BIBLIOTECAS JÁ ADICIONDADAS
	 */

	// Acessar o banco

	private final static String consultar = "SELECT especialidade data FROM consulta";
	private static final String URL = "jdbc:mysql://localhost:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
	private static final String USUARIO = "root";
	private static final String SENHA = "12345";

	// Padrão Observer
	private final List<Observador> observadores;

	public ConfirmarConsultaMB() {
		this.observadores = new ArrayList<>();

		this.observadores.add(new EnviarEmail());

	}

	public void confirmar(Paciente paciente, Consulta consulta) {
		// Lógica para o BD

		// Para cada observador, chama-se o método que irá realizar sua devida ação:
		for (Observador obs : this.observadores) {
			// obs.notificar(paciente, consulta);
		}
	}

	// método de enviar email
	public void enviarEmail() {
		// Recuperar dados da consulta
		Consulta consulta = new Consulta();
		Connection conexao;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			PreparedStatement sttmt = conexao.prepareStatement(consultar);
			ResultSet dados = sttmt.executeQuery("SELECT * FROM consulta");
			// consulta.setDataConsulta(dados.getDate(0));
			// consulta.setEspecialidade(dados.getString(0));
			consulta.isRealizada();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (consulta.isRealizada() == true) {
			Usuario user = new Usuario();
			EmailConsulta email = new EmailConsulta();
			String meuemail = email.getRemetente();
			String minhasenha = email.getSenha();
			String destinatario = user.getEmail();
			String msg = "teste";

			SimpleEmail emailconfig = new SimpleEmail();
			emailconfig.setHostName("smtp.gmail.com");
			emailconfig.setSmtpPort(465);
			emailconfig.setAuthenticator(new DefaultAuthenticator(meuemail, minhasenha));
			emailconfig.setSSLOnConnect(true);

			try {
				emailconfig.setFrom(meuemail);
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

}
