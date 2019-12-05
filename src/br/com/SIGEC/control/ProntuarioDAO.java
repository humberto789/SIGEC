package br.com.SIGEC.control;

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
	
	public static void cadastrarProntuario(Prontuario pront) {
		String cadastrar = "insert into pessoa(peso, altura, id, alergia,queixa, temperatura) value('?','?','?','?','?','?');";
		
		try {
			PreparedStatement sttmt = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(cadastrar);
			sttmt.setDouble(1, pront.getPeso());
			sttmt.setDouble(2, pront.getAltura());
			sttmt.setInt(3, pront.getId());
			sttmt.setString(4, pront.getAlergia());
			sttmt.setString(5, pront.getQueixa());
			sttmt.setDouble(6, pront.getTemperatura());
			
			sttmt.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
