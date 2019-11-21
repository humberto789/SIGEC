package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UsuarioDAO extends AbstractDao{

	public static boolean cadastrarUsuario(String email, String login, String senha, String cpf) {
		String sqlInsertPessoa = "insert into usuario (email, login, senha, ativo, id_pessoa) values (?, ?, ?, 1, (select id from pessoa where cpf = ?))";
		
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(sqlInsertPessoa);
			statement.setString(1, email);
			statement.setString(2, login);
			statement.setString(3, senha);
			statement.setString(4, cpf);
			
			int linhasAlteradas = statement.executeUpdate();
			if(linhasAlteradas > 0) return true;
			
			return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
