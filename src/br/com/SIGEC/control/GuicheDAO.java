package br.com.SIGEC.control;

import java.sql.PreparedStatement;

public class GuicheDAO extends AbstractDao{
	
	public static void guicheSenha() {
		
		String inserir_senha = "INSERT INTO fila (senha) value (?)";
		
		try {
			
			PreparedStatement sttm = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(inserir_senha);
			sttm.setInt(1, 1);
			sttm.execute();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
