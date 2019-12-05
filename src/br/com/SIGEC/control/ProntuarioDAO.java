package br.com.SIGEC.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.SIGEC.model.Atestado;
import br.com.SIGEC.model.Fila;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Prontuario;

public class ProntuarioDAO extends AbstractDao {

	private static final String SQL_SELECT_PRONTUARIO_POR_CPF = "SELECT prontuario.*, medico.crm, pessoaMedico.nome , pessoaPaciente.nome FROM prontuario INNER JOIN medico ON prontuario.id_medico = medico.id INNER JOIN pessoa pessoaMedico ON medico.id_pessoa = pessoaMedico.id INNER JOIN paciente ON prontuario.id_paciente = paciente.id INNER JOIN pessoa pessoaPaciente ON paciente.id_pessoa = pessoaPaciente.id WHERE pessoaPaciente.cpf = ?";
	private static final String SQL_INSERT_PRONTUARIO = "INSERT INTO prontuario(peso, altura, alergia, queixa, temperatura, id_medico, id_paciente) VALUES(?, ?, ?, ?, ?, (SELECT id FROM medico WHERE medico.crm = ?), (SELECT paciente.id FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id WHERE pessoa.cpf=?));";
	
	

	public static List<Prontuario> buscarProntuarioPorCPFDOPaciente(String cpf) {
		List<Prontuario> meusProntuarios = new ArrayList<>();
	
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_SELECT_PRONTUARIO_POR_CPF);
			statement.setString(1, cpf);
			ResultSet resultadoBusca = statement.executeQuery();

			while (resultadoBusca.next()) {

				Medico medico = new Medico();
				medico.setCrm(resultadoBusca.getString("medico.crm"));
				medico.getPessoa().setNomeCompleto(resultadoBusca.getString("pessoaMedico.nome"));
				
				Paciente paciente = new Paciente();
				paciente.getPessoa().setNomeCompleto(resultadoBusca.getString("pessoaPaciente.nome"));
				
				Prontuario prontuario = new Prontuario();
				prontuario.setPaciente(paciente);
				prontuario.setMedico(medico);
				prontuario.setAlergia(resultadoBusca.getString("prontuario.alergia"));
				prontuario.setAltura(resultadoBusca.getDouble("prontuario.altura"));
				prontuario.setPeso(resultadoBusca.getDouble("prontuario.peso"));
				prontuario.setQueixa(resultadoBusca.getString("prontuario.queixa"));
				prontuario.setTemperatura(resultadoBusca.getDouble("prontuario.temperatura"));

				meusProntuarios.add(prontuario);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return meusProntuarios;

	}
	
	public static boolean cadastrarProntuario(Prontuario prontuario) {
		try {
			Connection conexao = getConexao();
			if (conexao == null) {
				System.out.println("Conexao nula");
			}
			PreparedStatement statement = conexao.prepareStatement(SQL_INSERT_PRONTUARIO);
			statement.setDouble(1, prontuario.getPeso());
			statement.setDouble(2, prontuario.getAltura());
			statement.setString(3, prontuario.getAlergia());
			statement.setString(4, prontuario.getQueixa());
			statement.setDouble(5, prontuario.getTemperatura());
			statement.setString(6, prontuario.getMedico().getCrm());
			statement.setString(7, prontuario.getPaciente().getPessoa().getCpf());
			
			
			int linhasAlteradas = statement.executeUpdate();
			if (linhasAlteradas > 0)
				return true;

			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
