package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.SIGEC.model.Endereco;

public class EnderecoDAO {
	private static final String SQL_INSERT_ENDERECO = "INSERT INTO endereco (estado, cidade, bairro, cep, numero, rua, id_pessoa) VALUES (?, ?, ?, ?, ?, ?, (SELECT id FROM pessoa WHERE cpf = ?))";
	
	public static boolean cadastrarEndereco(Endereco endereco, String cpf_pessoa) {

		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_INSERT_ENDERECO);
			statement.setString(1, endereco.getEstado());
			statement.setString(2, endereco.getCidade());
			statement.setString(3, endereco.getBairro());
			statement.setString(4, endereco.getCep());
			statement.setString(5, endereco.getNumero());
			statement.setString(6, endereco.getRua());
			statement.setString(7, cpf_pessoa);

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
