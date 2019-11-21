package br.com.SIGEC.control;

import java.sql.Connection;

public abstract class AbstractDao {
	
	private static Connection conexao = null;
	
	public AbstractDao() {
		if (conexao == null) {
			conexao = ConexaoBanco.conexaoComBancoMySQL();
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}

}
