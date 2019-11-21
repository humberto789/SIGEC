package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Fila;
import br.com.SIGEC.model.Prontuario;

public class ProntuarioDAO extends AbstractDao {

	private static final String SQL_SELECT_PRONTUARIO_POR_CPF = "SELECT prontuario.*, pessoa.cpf FROM prontuario INNER JOIN paciente ON prontuario.id_paciente = paciente.id INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id WHERE cpf = ?";

	public static Prontuario buscarProntuarioPorCPFDOPaciente(String cpf) {
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL()
					.prepareStatement(SQL_SELECT_PRONTUARIO_POR_CPF);
			statement.setString(1, cpf);

			ResultSet resultadoBusca = statement.executeQuery();

			if (resultadoBusca.next()) {
				// Isso é temporario
				Prontuario prontuario = new Prontuario(resultadoBusca.getDouble("peso"),
						resultadoBusca.getDouble("altura"), resultadoBusca.getInt("id"),
						resultadoBusca.getString("alergia"), resultadoBusca.getString("queixa"),
						resultadoBusca.getDouble("temperatura"));

				return prontuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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