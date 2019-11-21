package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.SIGEC.model.Fila;

public class ChamarPacienteDAO extends AbstractDao {
	
	public static void recuperarLista(Fila fila) {
		String recuperarLista = "select senha from fila limit 1";
		
		try {
			PreparedStatement sttmt = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(recuperarLista);
			ResultSet dados = sttmt.executeQuery();
			dados.next();
			int numero = dados.getInt("senha");
			String numeroNome = Integer.toString(numero);
			fila.setSenha(numeroNome);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static String chamarProximo(Fila fila) {
			String atualizarLista = "delete from fila where fila equals '?'";
			String proximoPaciente = fila.getSenha();
			
			if (proximoPaciente != null) {
				try {
					PreparedStatement sttmt = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(atualizarLista);
					sttmt.setString(1, fila.getSenha());
					sttmt.execute();
				} catch (Exception e) {
					// TODO: handle exception
				} 
			} else {
				//Sem pacientes disponíveis \(*-*)/
				proximoPaciente = "Lista vazia";
			}
			return proximoPaciente;
	}
	
}
