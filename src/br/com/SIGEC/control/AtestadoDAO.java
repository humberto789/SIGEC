package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import br.com.SIGEC.model.Atestado;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Pessoa;

public class AtestadoDAO extends AbstractDao {

	private static final String SQL_CONSULTA_POR_CPF = "SELECT * FROM atestado ..... WHERE cpf = ?";
	private static final String SQL_INSERT = "INSERT INTO atestado (cid, dataEmissao, dataVencimento, id_medico, id_paciente) value(" + 
			"?, " + 
			"?, " + 
			"?, " + 
			"(select id from medico where crm = ?), " + 
			"(select paciente.id from paciente inner join pessoa on pessoa.id = paciente.id_pessoa where pessoa.cpf = ?)" + 
			");";

	/**
	 * Método responsável por recuperar todos os atestados de um determinado
	 * paciente, com base em seu CPF.
	 * 
	 * @param umCPF
	 *            um {@link String} que representa o CPF do paciente.
	 * @return um {@link List} contendo todos os {@link Atestado} do paciente.
	 */
	//public List<Atestado> buscarAtestadorPorCPF(String umCPF) {
		//List<Atestado> meusAtestados = new ArrayList<>();
		/*try {
			PreparedStatement preparedStatement = super.getConexao().prepareStatement(SQL_CONSULTA_POR_CPF);
			/// -----
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		/*
		Medico medico = new Medico("123");
		medico.setPessoa(new Pessoa(1, "111.111.111-11", "Médico da Silva", new Date(), "M"));
		Paciente lucas = new Paciente(1);
		lucas.setCpf("123.123.123-12");
		lucas.setNomeCompleto("Lucas Mariano");
		
		Date hoje = new Date();
		Calendar dataVencimento = Calendar.getInstance();
		dataVencimento.setTime(hoje);
		dataVencimento.add(Calendar.DAY_OF_YEAR, 3);

		meusAtestados.add(new Atestado(lucas, medico, hoje, dataVencimento.getTime(), "123", 1));
		meusAtestados.add(new Atestado(lucas, medico, hoje, dataVencimento.getTime(), "321", 2));
		meusAtestados.add(new Atestado(lucas, medico, hoje, dataVencimento.getTime(), "111", 3));
		meusAtestados.add(new Atestado(lucas, medico, hoje, dataVencimento.getTime(), "222", 4));
		meusAtestados.add(new Atestado(lucas, medico, hoje, dataVencimento.getTime(), "333", 5));
		
		return meusAtestados;
		*/
	//}
	
	/**
	 * Método responsável por emitir um novo atestado para um determinado paciente
	 * paciente com base no cpf, no crm médico, no CID e nas datas de emissão e 
	 * vencimento; 
	 * 
	 * @param umAtestado
	 */
	public static boolean gravarAtestado(Atestado umAtestado) {
		try {
			Connection conexao =getConexao();
			if(conexao==null) {
				System.out.println("Conexao nula");
			}
			PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);
			preparedStatement.setInt(1, umAtestado.getCid());
			preparedStatement.setDate(2, new Date(umAtestado.getDataEmissao().getTime()));
			preparedStatement.setDate(3, new Date(umAtestado.getDataVencimento().getTime()));
			preparedStatement.setString(4, umAtestado.getMedico().getCrm());
			preparedStatement.setString(5, umAtestado.getPaciente().getPessoa().getCpf());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
