package br.com.SIGEC.control;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.model.Usuario;

public class PessoaDAO {

	public static boolean cadastrarPessoa(Pessoa pessoa) {
		String sqlInsertPessoa = "insert into pessoa (cpf, nome, dataNascimento, sexo) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(sqlInsertPessoa);
			statement.setString(1, pessoa.getCpf());
			statement.setString(2, pessoa.getNomeCompleto());
			statement.setDate(3, new Date(pessoa.getDataNascimento().getTime()));
			statement.setString(4, pessoa.getSexo());
			
			int linhasAlteradas = statement.executeUpdate();
			if(linhasAlteradas > 0) return true;
			
			return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Pessoa buscarPessoaPorCpf(String cpf) {
		
		String sqlInsertPessoa = "select p.*, u.email, u.login, u.senha, u.ativo from pessoa p inner join usuario u on p.id = u.id_pessoa where p.cpf = ?";
		
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
						resultadoBusca.getString("sexo"));
				
				Usuario usuario = new Usuario();
				usuario.setSenha(resultadoBusca.getString("senha"));
				usuario.setAtivo(resultadoBusca.getBoolean("ativo"));
				usuario.setEmail(resultadoBusca.getNString("email"));
				usuario.setLogin(resultadoBusca.getString("login"));
				
				pessoa.setUsuario(usuario);
				
				return pessoa;
			}
			return null;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean bloquearPorCPF(String cpf) {
	String linhasAlteradas = "update usuario set ativo = false where id_pessoa= (select id from pessoa where cpf = ?)";
	
	try {
		PreparedStatement statement =  ConexaoBanco.conexaoComBancoMySQL().prepareStatement(linhasAlteradas);
		statement.setString(1, cpf);
		int linhas = statement.executeUpdate();
		if (linhas == 1) {
			return true;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false; 
	
	
	}
}
