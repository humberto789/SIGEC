package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.web.mbean.Consulta;

public class MedicoDAO extends AbstractDao {

	private static final String SQL_INSERT_MEDICO = "insert into medico (crm, id_pessoa) values (?, (select id from pessoa where cpf = ?)); ";
	private static final String SQL_SELECT_MEDICO_CONSULTAS = "SELECT cons.dataConsulta, pes.nome, pes.cpf FROM consulta cons INNER JOIN paciente p ON cons.id_paciente = p.id " + 
																															  "INNER JOIN pessoa pes ON p.id_pessoa = pes.id " + 
																															  "WHERE id_medico = ?;";

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
	
	/**
	 * De onde vai vir esse m�dico? Ele vai estar logado? Vai colocar o CPF dele para pesquisar as consultas?
	 * @param medico - M�dico das consultas
	 * @return - Lista de consultas desse m�dico
	 */
	
	public static List<Consulta> listarConsultas(Medico medico) {
		List<Consulta> consultas = new ArrayList<>();
		
		try {
			PreparedStatement stmt = AbstractDao.getConexao().prepareStatement(SQL_SELECT_MEDICO_CONSULTAS);
			stmt.setInt(1, medico.getId());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Paciente paciente = new Paciente();
				Pessoa pessoa = new Pessoa();
				pessoa.setNomeCompleto(rs.getString("pes.nome"));
				pessoa.setCpf(rs.getString("pes.cpf"));
				paciente.setPessoa(pessoa);
				
				Consulta consulta = new Consulta();
				consulta.setMedico(medico.getPessoa().getNomeCompleto());	
				consulta.setDataConsulta(rs.getDate("cons.dataConsulta"));
				consulta.setPaciente(pessoa.getNomeCompleto());

				consultas.add(consulta);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return consultas;
	}

}
