package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Usuario;


public class UsuarioDAO extends AbstractDao{

	private final static String SQL_SELECT_USUARIO_POR_LOGIN_E_SENHA = "SELECT * FROM usuario WHERE login=? AND senha=?;";
	private final static String SQL_INSERIR = "INSERT INTO USUARIO (email, login, senha, ativo, id_pessoa) VALUE (?, ?, ?, 1 (SELECT id FROM pessoa WHERE cpf=?));";
	
	public static boolean cadastrarUsuario(String email, String login, String senha, String cpf) {
		
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_INSERIR);
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
	
	/**
	 * Método responsável por recuperar um usuario, com base no seu login e na sua senha.
	 * 
	 * @param umLogin um {@link String} que representa o login do usuario
	 * @param umaSenha um {@link String} que representa a senha do usuario
	 * 
	 * @return {@link Usuario} do usuario
	 */
	public static Usuario buscarUsuarioPorLoginESenha(String umLogin, String umaSenha) {

		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_SELECT_USUARIO_POR_LOGIN_E_SENHA);
			statement.setString(1, umLogin);
			statement.setString(2, umaSenha);

			ResultSet resultadoBusca = statement.executeQuery();

			if (resultadoBusca.next()) {

				Usuario usuario = new Usuario();
				usuario.setSenha(resultadoBusca.getString("senha"));
				usuario.setAtivo(resultadoBusca.getBoolean("ativo"));
				usuario.setEmail(resultadoBusca.getString("email"));
				usuario.setLogin(resultadoBusca.getString("login"));

				return usuario;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
