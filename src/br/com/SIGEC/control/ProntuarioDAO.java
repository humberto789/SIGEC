package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Prontuario;

public class ProntuarioDAO {

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

}