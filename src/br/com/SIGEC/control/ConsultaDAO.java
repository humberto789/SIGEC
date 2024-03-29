package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.SIGEC.model.Consulta;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;

import br.com.SIGEC.model.Pessoa;


public class ConsultaDAO extends AbstractDao{
	private static final String SQL_INSERT_CONSULTA = "INSERT INTO consulta(id_medico, id_paciente, horario, realizada) VALUES ((SELECT id FROM medico WHERE crm=?), (SELECT paciente.id FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id WHERE pessoa.cpf=?), ?, 0)";
	private static final String SQL_SELECT_CONSULTA_POR_HORARIO_POR_CPF_PACIENTE_POR_CRM_MEDICO = "SELECT * FROM consulta INNER JOIN paciente ON consulta.id_paciente = paciente.id INNER JOIN medico ON consulta.id_medico = medico.id INNER JOIN pessoa ON pessoa.id = paciente.id_pessoa WHERE consulta.horario=? AND medico.crm=? AND pessoa.cpf=?";
	private static final String SQL_SELECT_MEDICO_CONSULTA = "SELECT cons.id, cons.horario, pes.nome, pes.cpf FROM consulta cons INNER JOIN paciente p ON cons.id_paciente = p.id " +
																	" INNER JOIN pessoa pes ON p.id_pessoa = pes.id" + 
																	" WHERE cons.id = ?;";
	private static final String SQL_DELETE_CONSULTA = "DELETE FROM consulta WHERE id = ?;";
	private static final String SQL_UPDATE_CONSULTA = "UPDATE consulta SET horario = ? WHERE id = ?;";
	


	private static final String SQL_SELECT_CONSULTA_POR_CPF_DO_PACIENTE = "SELECT consulta.*, medico.crm, pessoaMedico.nome , pessoaPaciente.nome FROM consulta INNER JOIN medico ON consulta.id_medico = medico.id INNER JOIN pessoa pessoaMedico ON medico.id_pessoa = pessoaMedico.id INNER JOIN paciente ON consulta.id_paciente = paciente.id INNER JOIN pessoa pessoaPaciente ON paciente.id_pessoa = pessoaPaciente.id WHERE pessoaPaciente.cpf = ?";
	private static final String SQL_UPDATE_CONFIRMAR_CONSULTA = "UPDATE consulta SET realizada = true WHERE id_paciente = (SELECT paciente.id FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id WHERE pessoa.cpf=?) AND id_medico = (SELECT id FROM medico WHERE crm = ?) AND horario = ?";
	
	public static boolean confirmarConsulta(Consulta consulta) {
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_UPDATE_CONFIRMAR_CONSULTA);
			statement.setString(1, consulta.getPaciente().getPessoa().getCpf());
			statement.setString(2, consulta.getMedico().getCrm());
			statement.setTimestamp(3, new Timestamp(consulta.getHorario().getTime()));

			int linhasAlteradas = statement.executeUpdate();
			if (linhasAlteradas > 0)
				return true;

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean cadastrarConsulta(Consulta consulta) {
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_INSERT_CONSULTA);
			statement.setString(1, consulta.getMedico().getCrm());
			statement.setString(2, consulta.getPaciente().getPessoa().getCpf());
			statement.setTimestamp(3, new Timestamp(consulta.getHorario().getTime()));

			int linhasAlteradas = statement.executeUpdate();
			if (linhasAlteradas > 0)
				return true;

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean verificarSeConsultaExiste(Consulta consulta) {
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_SELECT_CONSULTA_POR_HORARIO_POR_CPF_PACIENTE_POR_CRM_MEDICO);
			statement.setTimestamp(1, new Timestamp(consulta.getHorario().getTime()));
			statement.setString(2, consulta.getMedico().getCrm());
			statement.setString(3, consulta.getPaciente().getPessoa().getCpf());

			ResultSet resultadoBusca = statement.executeQuery();

			if (resultadoBusca.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param id
	 * @param medico - M�dico logado
	 * @return
	 */
	public static Consulta buscar(int id, Medico medico) {
		Consulta consulta = null;
		
		try {
			PreparedStatement stmt = AbstractDao.getConexao().prepareStatement(SQL_SELECT_MEDICO_CONSULTA);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				Paciente paciente = new Paciente();
				Pessoa pessoa = new Pessoa();
				pessoa.setNomeCompleto(rs.getString("pes.nome"));
				pessoa.setCpf(rs.getString("pes.cpf"));
				paciente.setPessoa(pessoa);
				
				consulta = new Consulta();
				consulta.setPaciente(paciente);
				consulta.setHorario(new java.util.Date(rs.getTimestamp("cons.horario").getTime()));
				consulta.setMedico(medico);
				consulta.setId(rs.getInt("cons.id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return consulta;	
	}

	public static void cancelarConsulta(Consulta consulta) {	
		 try {
			PreparedStatement stmt = AbstractDao.getConexao().prepareStatement(SQL_DELETE_CONSULTA);
			stmt.setInt(1, consulta.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	      
	}

	public static void atualizar(Consulta consulta) {
		 try {
			PreparedStatement stmt = AbstractDao.getConexao().prepareStatement(SQL_UPDATE_CONSULTA);
			stmt.setTimestamp(1, new java.sql.Timestamp(consulta.getHorario().getTime()));
			stmt.setInt(2, consulta.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	   
	}
	
	public static List<Consulta> buscarConsultaPorCPF(String cpf) {
		List<Consulta> minhasConsultas = new ArrayList<>();
		try {
			PreparedStatement stmt = getConexao().prepareStatement(SQL_SELECT_CONSULTA_POR_CPF_DO_PACIENTE);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Medico medico = new Medico();
				medico.setCrm(rs.getString("medico.crm"));
				medico.getPessoa().setNomeCompleto(rs.getString("pessoaMedico.nome"));

				Paciente paciente = new Paciente();
				paciente.getPessoa().setNomeCompleto(rs.getString("pessoaPaciente.nome"));

				Consulta consulta = new Consulta();
				consulta.setHorario(new java.util.Date(rs.getTimestamp("consulta.horario").getTime()));
				consulta.setPaciente(paciente);
				consulta.setMedico(medico);

				minhasConsultas.add(consulta);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return minhasConsultas;
	}
}
