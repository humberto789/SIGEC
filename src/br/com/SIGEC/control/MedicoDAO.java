package br.com.SIGEC.control;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.SIGEC.model.Medico;

public class MedicoDAO extends AbstractDao {

	public static boolean cadastroMedico(Medico medico) {
		String sqlInsertMedico = "insert into medico (crm, id_pessoa) values (?, select id from medico where cpf = ?); ";
		
		if(PessoaDAO.buscarPessoaPorCpf(medico.getPessoa().getCpf()) == null) {
			if(PessoaDAO.cadastrarPessoa(medico.getPessoa())) {
				try {
					PreparedStatement statement = ConexaoBanco.conexaoComBancoMySQL().prepareStatement(sqlInsertMedico);
					
					statement.setString(1, medico.getCrm());
					statement.setString(2, medico.getPessoa().getCpf());
					
					int linhasAlteradas = statement.executeUpdate();
					if(linhasAlteradas > 0) return true;
					
					return false;
				
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
			
			return false;
		}
		
		return false;
	}
	
}
