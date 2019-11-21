package br.com.SIGEC.web.mbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.SIGEC.model.Fila;

//Recupera lista e adiciona a lista daqui
//Deleta valor da lista de lá

public class ChamarPaciente {
	//botão próximo acionado
	
	private final static String recuperarLista = "select senha from fila";
	private final static String atualizarLista = "delete from fila where fila equals '?'"; // Senha atual
	
	private static final String URL = "jdbc:mysql://localhost:3306/SIGEC?useLegacyDatetimeCode=false&serverTimezone=America/Fortaleza";
	private static final String USUARIO = "root";
	private static final String SENHA="12345";
	
	//Recupera e cria lista (Conexão com o banco)
	
	Fila fila = new Fila();
	
	public class RecuperaLista {
		
		public Connection conexaoComBancoMySQL(){
			Connection conexao;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				PreparedStatement sttmt = conexao.prepareStatement(recuperarLista);
				ResultSet dados = sttmt.executeQuery();
				fila.setSenha(dados.getString(0));
				return conexao;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
	@SuppressWarnings("unused") //não usado em nada, por enquanto
	private String chamarProximo(Fila fila) {
		String proximoPaciente = fila.getSenha();
		if (proximoPaciente != null) {
			//Atualiza lista (Conexão com o banco + String atualizar lista)
				
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
			//Sem pacientes disponíveis \(*-*)/
			proximoPaciente = "Lista vazia";
		}
		
		return proximoPaciente;
		
	}
	
	
}
