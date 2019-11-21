package br.com.SIGEC.web.mbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Fila;

//Recupera lista e adiciona a lista daqui
//Deleta valor da lista de l�

public class ChamarPaciente {
	//bot�o pr�ximo acionado
	
	private final static String recuperarLista = "select senha from fila limit 1";
	private final static String atualizarLista = "delete from fila where fila equals '?'"; // Senha atual
	
	private static final String URL = "jdbc:mysql://localhost:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
	private static final String USUARIO = "root";
	private static final String SENHA="aluno";
	
	//Recupera e cria lista (Conex�o com o banco)
		
	public void RecuperaLista(Fila fila) {
		
			Connection conexao;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				PreparedStatement sttmt = conexao.prepareStatement(recuperarLista);
				ResultSet dados = sttmt.executeQuery();
				dados.next();
				int numero = dados.getInt("senha");
				String numeroNome = Integer.toString(numero);
				
				
				fila.setSenha(numeroNome);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		
	}
	
	public String chamarProximo(Fila fila) {
		String proximoPaciente = fila.getSenha();
		if (proximoPaciente != null) {
			//Atualiza lista (Conex�o com o banco + String atualizar lista)
				
					Connection conexao;
					
					try {
						Class.forName("com.mysql.jdbc.Driver");
						conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
						PreparedStatement sttmt = conexao.prepareStatement(atualizarLista);
						sttmt.setString(1, fila.getSenha());
						sttmt.execute();
					} catch (SQLException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				
			
		} else {
			//Sem pacientes dispon�veis \(*-*)/
			proximoPaciente = "Lista vazia";
		}
		
		return proximoPaciente;
		
	}
	
	
}
