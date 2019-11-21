package br.com.SIGEC.web.mbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.SIGEC.model.Fila;

@ManagedBean                                   
public class GuicheMB {	
	
	private static final String inserir_senha = "INSERT INTO fila (senha) value (?)";
	
	Fila fila = new Fila();
	
	int n = 1;
	
	private static final String URL = "jdbc:mysql://localhost:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
	private static final String USUARIO = "root";
	private static final String SENHA="aluno";
	
	public static Connection conexaoComBancoMySQL(){
		Connection conexao;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			PreparedStatement sttm = conexao.prepareStatement(inserir_senha);
			sttm.setInt(0, 1);
			sttm.execute();
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public GuicheMB() {

	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}
	
	
	
}