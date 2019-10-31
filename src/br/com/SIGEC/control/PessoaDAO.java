package br.com.SIGEC.control;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Pessoa;

public class PessoaDAO {

	public static boolean cadastrarPessoa(Pessoa pessoa) {
		String sqlInsertPessoa = "insert into pessoa (cpf, nome, dataNascimento, sexo) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(sqlInsertPessoa);
			statement.setString(1, pessoa.getCpf());
			statement.setString(2, pessoa.getNomeCompleto());
			statement.setDate(3, new Date(pessoa.getDataNascimento().getTime()));
			statement.setInt(4, pessoa.getSexo());
			
			int linhasAlteradas = statement.executeUpdate();
			if(linhasAlteradas > 0) return true;
			
			return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Pessoa buscarPessoaPorCpf(String cpf) {
		
		String sqlInsertPessoa = "select from pessoa where cpf = ?";
		
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(sqlInsertPessoa);
			statement.setString(1, cpf);
			
			ResultSet resultadoBusca = statement.executeQuery();
			
			if(resultadoBusca.next()) {
				//Isso é temporario
				Pessoa pessoa = new Pessoa(resultadoBusca.getInt("id"), 
						resultadoBusca.getString("cpf"),
						resultadoBusca.getString("nome"),
						resultadoBusca.getDate("dataNascimento"),
						resultadoBusca.getInt("sexo"));
				
				return pessoa;
			}
			return null;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
