package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.SIGEC.model.Consulta;


public class ConsultaDAO {
	private static final String SQL_INSERT_CONSULTA = "INSERT INTO consulta(id_medico, id_paciente, horario) VALUES ((SELECT id FROM medico WHERE crm=?), (SELECT paciente.id FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id WHERE pessoa.cpf=?), ?)";
	private static final String SQL_SELECT_CONSULTA_POR_HORARIO_POR_CPF_PACIENTE_POR_CRM_MEDICO = "SELECT * FROM consulta INNER JOIN paciente ON consulta.id_paciente = paciente.id INNER JOIN medico ON consulta.id_medico = medico.id INNER JOIN pessoa ON pessoa.id = paciente.id_pessoa WHERE consulta.horario=? AND medico.crm=? AND pessoa.cpf=?";
	
	public static void confirmarConsulta(Consulta consulta) {
		
	}
	
	public static boolean cadastrarConsulta(Consulta consulta) {
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_INSERT_CONSULTA);
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
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_SELECT_CONSULTA_POR_HORARIO_POR_CPF_PACIENTE_POR_CRM_MEDICO);
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
}
