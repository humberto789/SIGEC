package br.com.SIGEC.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.VerConsultas;

public class VerConsultasDAO extends AbstractDao  {
	
	
	private static final String SQL_CONSULTA_POR_CPF = "SELECT consultas.dataConsulta, consultas.especialidade, medico.crm, pessoaMedico.nome as medico, pessoaPaciente.nome as paciente FROM atestado INNER JOIN medico ON consultas.id_medico = medico.id INNER JOIN pessoa pessoaMedico ON medico.id_pessoa = pessoaMedico.id INNER JOIN paciente ON consultas.id_paciente = paciente.id INNER JOIN pessoa pessoaPaciente ON paciente.id_pessoa = pessoaPaciente.id WHERE pessoaPaciente.cpf = ?";
	private static final String SQL_INSERT = "INSERT INTO consultas ( dataConsulta, especialidade, id_medico, id_paciente) value(" + 
			"?, " + 
			"?, " + 
			"?, " + 
			"(select id from medico where crm = ?), " + 
			"(select paciente.id from paciente inner join pessoa on pessoa.id = paciente.id_pessoa where pessoa.cpf = ?)" + 
			");";

	
	public List<VerConsultas> buscarConsultaPorCPF(String cpf) {
		List<VerConsultas> Consulta = new ArrayList<>();
		List<VerConsultas> minhasConsultas = null;
		try {
			PreparedStatement stmt = super.getConexao().prepareStatement(SQL_CONSULTA_POR_CPF);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Medico medico = new Medico();
				medico.setCrm(rs.getString("medico.crm"));
				medico.getPessoa().setNomeCompleto(rs.getString("medico"));

				Paciente paciente = new Paciente();
				paciente.getPessoa().setNomeCompleto(rs.getString("paciente"));
				

				VerConsultas consulta = new VerConsultas();
				consulta.setDataConsulta(new java.util.Date(rs.getDate("consulta.dataConsulta").getTime()));
				consulta.setPaciente(paciente);
				consulta.setMedico(medico);

				minhasConsultas.add(consulta);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return minhasConsultas;
	}

	
	public static boolean gravarConsulta(VerConsultas umConsulta) {
		try {
			Connection conexao = getConexao();
			if (conexao == null) {
				System.out.println("Conexao nula");
			}
			PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);
			preparedStatement.setDate(1, new Date(umConsulta.getDataConsulta().getTime()));
			preparedStatement.setString(2, umConsulta.getMedico().getCrm());
			preparedStatement.setString(3, umConsulta.getPaciente().getPessoa().getCpf());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}