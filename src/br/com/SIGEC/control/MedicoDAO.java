package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.model.Usuario;
import br.com.SIGEC.web.mbean.Consulta;

public class MedicoDAO extends AbstractDao {

	private static final String SQL_INSERT_MEDICO = "insert into medico (crm, id_pessoa) values (?, (select id from pessoa where cpf = ?)); ";
	private static final String SQL_SELECT_MEDICO_CONSULTAS = "SELECT cons.dataConsulta, pes.nome, pes.cpf FROM consulta cons INNER JOIN paciente p ON cons.id_paciente = p.id " + 
																															  "INNER JOIN pessoa pes ON p.id_pessoa = pes.id " + 
																															  "WHERE id_medico = ?;";
	private static final String SQL_SELECT_MEDICO_POR_LOGIN = "SELECT * FROM medico INNER JOIN pessoa ON medico.id_pessoa = pessoa.id INNER JOIN usuario ON usuario.id_pessoa = pessoa.id WHERE usuario.login = ?;";
	private static final String SQL_SELECT_MEDICO_POR_CRM = "SELECT * FROM medico INNER JOIN pessoa ON medico.id_pessoa = pessoa.id INNER JOIN usuario ON usuario.id_pessoa = pessoa.id WHERE medico.crm = ?;";

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
	 * De onde vai vir esse médico? Ele vai estar logado? Vai colocar o CPF dele para pesquisar as consultas?
	 * 
	 * O crm do médico vai estar na sessão, para facilitar sua vida você pode pegar sair fazendo teste com um crm fixo, depois
	 * 
	 * eu volto aqui e faço a ligação com a sessão
	 * ass: Humberto
	 * 
	 * @param medico - Médico das consultas
	 * @return - Lista de consultas desse médico
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
	
	public static Medico buscarMedicoPeloCRM(String umCRM) {
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_SELECT_MEDICO_POR_CRM);
			statement.setString(1, umCRM);

			ResultSet resultadoBusca = statement.executeQuery();

			if (resultadoBusca.next()) {
				Pessoa pessoa = new Pessoa(resultadoBusca.getInt("pessoa.id"), resultadoBusca.getString("pessoa.cpf"),
						resultadoBusca.getString("pessoa.nome"), new java.util.Date(resultadoBusca.getDate("pessoa.dataNascimento").getTime()),
						resultadoBusca.getString("pessoa.sexo"));

				Usuario usuario = new Usuario();
				usuario.setId(resultadoBusca.getInt("usuario.id"));
				usuario.setSenha(resultadoBusca.getString("usuario.senha"));
				usuario.setAtivo(resultadoBusca.getBoolean("usuario.ativo"));
				usuario.setEmail(resultadoBusca.getString("usuario.email"));
				usuario.setLogin(resultadoBusca.getString("usuario.login"));

				pessoa.setUsuario(usuario);
				
				Medico medico = new Medico(resultadoBusca.getString("medico.crm"), resultadoBusca.getInt("medico.id"), pessoa);
				
			
				return medico;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Medico buscarMedicoPeloLoginDoUsuario(String umLogin) {
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_SELECT_MEDICO_POR_LOGIN);
			statement.setString(1, umLogin);

			ResultSet resultadoBusca = statement.executeQuery();

			if (resultadoBusca.next()) {
				Pessoa pessoa = new Pessoa(resultadoBusca.getInt("pessoa.id"), resultadoBusca.getString("pessoa.cpf"),
						resultadoBusca.getString("pessoa.nome"), new java.util.Date(resultadoBusca.getDate("pessoa.dataNascimento").getTime()),
						resultadoBusca.getString("pessoa.sexo"));

				Usuario usuario = new Usuario();
				usuario.setId(resultadoBusca.getInt("usuario.id"));
				usuario.setSenha(resultadoBusca.getString("usuario.senha"));
				usuario.setAtivo(resultadoBusca.getBoolean("usuario.ativo"));
				usuario.setEmail(resultadoBusca.getString("usuario.email"));
				usuario.setLogin(resultadoBusca.getString("usuario.login"));

				pessoa.setUsuario(usuario);
				
				Medico medico = new Medico(resultadoBusca.getString("medico.crm"), resultadoBusca.getInt("medico.id"), pessoa);
				
			
				return medico;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
