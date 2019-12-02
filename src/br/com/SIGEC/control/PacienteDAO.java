package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.model.Usuario;

public class PacienteDAO extends AbstractDao {
	private static final String SQL_INSERT_PACIENTE = "INSERT INTO paciente ( id_pessoa) VALUES ((SELECT id FROM pessoa WHERE cpf = ?));";
	private static final String SQL_SELECT_PACIENTE_POR_LOGIN = "SELECT * FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id INNER JOIN usuario ON usuario.id_pessoa = pessoa.id WHERE usuario.login = ?;";
	private static final String SQL_SELECT_PACIENTE_POR_CPF = "SELECT * FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id INNER JOIN usuario ON usuario.id_pessoa = pessoa.id WHERE pessoa.cpf = ?;";

	
	public static boolean cadastrarPaciente(Paciente paciente) {

		if (PessoaDAO.buscarPessoaPorCpf(paciente.getPessoa().getCpf()) == null) {
			if (PessoaDAO.cadastrarPessoa(paciente.getPessoa())) {
				if (UsuarioDAO.cadastrarUsuario(paciente.getPessoa().getUsuario().getEmail(),
						paciente.getPessoa().getCpf(), paciente.getPessoa().getUsuario().getSenha(),
						paciente.getPessoa().getCpf())) {
					try {
						PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL()
								.prepareStatement(SQL_INSERT_PACIENTE);

						statement.setString(1, paciente.getPessoa().getCpf());

						int linhasAlteradas = statement.executeUpdate();
						if (linhasAlteradas > 0)
							return true;

						return false;

					} catch (SQLException e) {
						e.printStackTrace();
						return false;
					}
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
	public static Paciente buscarPacientePeloCPF(String umCPF) {
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_SELECT_PACIENTE_POR_CPF);
			statement.setString(1, umCPF);

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

				Paciente paciente = new Paciente(resultadoBusca.getInt("paciente.id"), pessoa);
				
			
				return paciente;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Paciente buscarPacientePeloLoginDoUsuario(String umLogin) {
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_SELECT_PACIENTE_POR_LOGIN);
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

				Paciente paciente = new Paciente(resultadoBusca.getInt("paciente.id"), pessoa);
				
			
				return paciente;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
