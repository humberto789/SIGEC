package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.SIGEC.model.Fila;

public class FilaDAO extends AbstractDao{
	
	private static final String SQL_INSERT_SENHA = "INSERT fila(senha, chamado, id_consulta) VALUES (?, false, (SELECT consulta.id FROM consulta INNER JOIN paciente ON consulta.id_paciente = paciente.id INNER JOIN medico ON consulta.id_medico = medico.id INNER JOIN pessoa ON pessoa.id = paciente.id_pessoa WHERE consulta.horario=? AND medico.crm=? AND pessoa.cpf=?))";
	private static final String SQL_SELECT_FILA_POR_SENHA = "SELECT * FROM fila WHERE senha = ?";
	private static final String SQL_UPDATE_CHAMAR_PROXIMA_SENHA = "UPDATE fila SET chamado = true WHERE senha = ?";
	private static final String SQL_SELECT_ULTIMA_SENHA_NAO_CHAMADA = "SELECT senha FROM fila as filas WHERE chamado = false ORDER BY id LIMIT 1";
	
	public static boolean cadastrarSenha(Fila fila) {
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_INSERT_SENHA);
			statement.setString(1, fila.getSenha());
			statement.setTimestamp(2, new Timestamp(fila.getConsulta().getHorario().getTime()));
			statement.setString(3, fila.getConsulta().getMedico().getCrm());
			statement.setString(4, fila.getConsulta().getPaciente().getPessoa().getCpf());

			int linhasAlteradas = statement.executeUpdate();
			if (linhasAlteradas > 0)
				return true;

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean verificarSeSenhaExiste(String senha) {
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_SELECT_FILA_POR_SENHA);
			statement.setString(1, senha);

			ResultSet resultadoBusca = statement.executeQuery();

			if (resultadoBusca.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String recuperarUltimaSenha() {
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_SELECT_ULTIMA_SENHA_NAO_CHAMADA);
			
			ResultSet resultadoBusca = statement.executeQuery();

			String ultima_senha = "";
			
			if (resultadoBusca.next()) {
				ultima_senha = resultadoBusca.getString("senha");
				return ultima_senha;
			}else {
				return "SEM PACIENTES";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return "OCORREU UM ERRO";
		}
	}
	
	public static String chamarProximo() {
		try {
			PreparedStatement statement = getConexao().prepareStatement(SQL_UPDATE_CHAMAR_PROXIMA_SENHA);
			
			String senha = recuperarUltimaSenha();
			
			statement.setString(1, senha);
			
			int linhas_alteradas = statement.executeUpdate();
			
			if(linhas_alteradas>0) {
				return senha;
			}
			return "OCORREU UM ERRO";
			

		} catch (SQLException e) {
			e.printStackTrace();
			return "OCORREU UM ERRO";
		}
	}
}
