package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.SIGEC.model.Paciente;

public class PacienteDAO extends AbstractDao {
	private static final String SQL_INSERT_PACIENTE = "insert into paciente ( id_pessoa) values ((select id from pessoa where cpf = ?)); ";

	public static boolean cadastroPaciente(Paciente paciente) {

		if (PessoaDAO.buscarPessoaPorCpf(paciente.getPessoa().getCpf()) == null) {
			if (PessoaDAO.cadastrarPessoa(paciente.getPessoa())) {
				if (UsuarioDAO.cadastrarUsuario(paciente.getPessoa().getUsuario().getEmail(),
						// LOGIN!!!!!!!!!!!!!!!!!!!!!
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

}
