package br.com.SIGEC.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

	private static final String URL = "jdbc:mysql://localhost:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
	private static final String USUARIO = "root";
	private static final String SENHA="aluno";
	
	public static Connection conexaoComBancoMySQL(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
