package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.SIGEC.model.Atestado;
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

	
	public List<VerConsultas> buscarVerConsultasPorCPF() {
		List<VerConsultas> minhasConsultas = new ArrayList<>();
		try {
			PreparedStatement stmt = super.getConexao().prepareStatement(SQL_CONSULTA_POR_CPF);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Medico medico = new Medico();
				medico.getPessoa().setNomeCompleto(rs.getString("pessoaMedico.nome"));
				
				Paciente paciente = new Paciente();
				paciente.getPessoa().setNomeCompleto(rs.getString("pessoaPaciente.nome"));
				
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return minhasConsultas;
}
	
}