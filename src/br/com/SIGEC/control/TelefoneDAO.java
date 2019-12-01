package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.SIGEC.model.Telefone;

public class TelefoneDAO extends AbstractDao{
	
	private static final String SQL_INSERT_TELEFONE = "INSERT INTO telefone (numero, id_pessoa) VALUES (?, (SELECT id FROM pessoa WHERE cpf = ?))";
	
	public static boolean cadastrarTelefone(Telefone telefone, String cpf_pessoa) {

		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(SQL_INSERT_TELEFONE);
			statement.setInt(1, telefone.getNumero());
			statement.setString(2, cpf_pessoa);

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
