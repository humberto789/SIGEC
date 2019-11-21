package br.com.SIGEC.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

	private static final String URL = "jdbc:mysql://10.225.6.15:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
	private static final String USUARIO = "aluno";
	private static final String SENHA="aluno";
	
	public static Connection conexaoComBancoMySQL(){
		Connection conexao;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
