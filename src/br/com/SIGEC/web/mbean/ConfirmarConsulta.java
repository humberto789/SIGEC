package br.com.SIGEC.web.mbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import br.com.SIGEC.model.EmailConsulta;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Usuario;
import br.com.SIGEC.observer.EnviarEmail;
import br.com.SIGEC.observer.Observador;

@ManagedBean
public class ConfirmarConsulta {

	// Acessar o banco

	private final static String consultar = "SELECT especialidade data FROM consulta";
	private static final String URL = "jdbc:mysql://localhost:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
	private static final String USUARIO = "root";
	private static final String SENHA = "12345";
	
	//Padrão Observer
	private final List<Observador> observadores;
	
	public ConfirmarConsulta() {
		this.observadores = new ArrayList<>();
		
		this.observadores.add(new EnviarEmail());
	}
	
	public void confirmar(Paciente paciente, Consulta consulta) {
		//Lógica para o BD
		
		//Para cada observador, chama-se o método que irá realizar sua devida ação:
		for (Observador obs : this.observadores) {
			obs.notificar(paciente, consulta);
		}
	}
	
	

	// Recuperar dados da consulta

	public void RecuperaConsulta(Consulta consulta) {

		Connection conexao;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			PreparedStatement sttmt = conexao.prepareStatement(consultar);
			ResultSet dados = sttmt.executeQuery("SELECT * FROM consulta");
			consulta.setDataConsulta(dados.getDate(0));
			consulta.setEspecialidade(dados.getString(0));

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		

	}
	
	

	// método de enviar email
	public void enviarEmail(EmailConsulta email, Usuario user) {
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


