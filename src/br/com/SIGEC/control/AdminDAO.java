package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Admin;
import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.model.Usuario;

public class AdminDAO {
	
	private static final String SQL_SELECT_ADMIN_POR_LOGIN = "SELECT * FROM administrador INNER JOIN pessoa ON administrador.id_pessoa = pessoa.id INNER JOIN usuario ON usuario.id_pessoa = pessoa.id WHERE usuario.login = ?;";

	
	public static Admin buscarAdminPeloLoginDoUsuario(String umLogin) {
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_SELECT_ADMIN_POR_LOGIN);
			statement.setString(1, umLogin);

			ResultSet resultadoBusca = statement.executeQuery();

			if (resultadoBusca.next()) {
				Pessoa pessoa = new Pessoa(resultadoBusca.getInt("id"), resultadoBusca.getString("cpf"),
						resultadoBusca.getString("nome"), new java.util.Date(resultadoBusca.getDate("dataNascimento").getTime()),
						resultadoBusca.getString("sexo"));

				Usuario usuario = new Usuario();
				usuario.setId(resultadoBusca.getInt("usuario.id"));
				usuario.setSenha(resultadoBusca.getString("senha"));
				usuario.setAtivo(resultadoBusca.getBoolean("ativo"));
				usuario.setEmail(resultadoBusca.getString("email"));
				usuario.setLogin(resultadoBusca.getString("login"));

				pessoa.setUsuario(usuario);
				
				Admin admin = new Admin(resultadoBusca.getInt("administrador.id"), pessoa);
				
			
				return admin;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
