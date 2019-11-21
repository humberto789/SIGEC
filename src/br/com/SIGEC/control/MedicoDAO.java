package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.SIGEC.model.Medico;

public class MedicoDAO extends AbstractDao {

	private static final String SQL_INSERT_MEDICO = "insert into medico (crm, id_pessoa) values (?, (select id from pessoa where cpf = ?)); ";

	public static boolean cadastroMedico(Medico medico) {

		if (PessoaDAO.buscarPessoaPorCpf(medico.getPessoa().getCpf()) == null) {
			if (PessoaDAO.cadastrarPessoa(medico.getPessoa())) {
				if (UsuarioDAO.cadastrarUsuario(medico.getPessoa().getUsuario().getEmail(), medico.getCrm(),
						medico.getPessoa().getUsuario().getSenha(), medico.getPessoa().getCpf())) {
					try {
						PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL()
								.prepareStatement(SQL_INSERT_MEDICO);

						statement.setString(1, medico.getCrm());
						statement.setString(2, medico.getPessoa().getCpf());

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
