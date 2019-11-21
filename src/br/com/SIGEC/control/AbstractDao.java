package br.com.SIGEC.control;

import java.sql.Connection;

public abstract class AbstractDao {
	
	private static Connection conexao = null;
	
	public AbstractDao() {
	}
	
	public static Connection getConexao() {
		if (conexao == null) {
			conexao = ConexaoBanco.conexaoComBancoMySQL();
		}
		return conexao;
	}

}
