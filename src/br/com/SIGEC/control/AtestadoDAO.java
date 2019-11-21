package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.SIGEC.model.Atestado;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Pessoa;

public class AtestadoDAO extends AbstractDao {

	private static final String SQL_CONSULTA_POR_CPF = "SELECT * FROM atestado ..... WHERE cpf = ?";
	private static final String SQL_INSERT = "";

	/**
	 * Método responsável por recuperar todos os atestados de um determinado
	 * paciente, com base em seu CPF.
	 * 
	 * @param umCPF
	 *            um {@link String} que representa o CPF do paciente.
	 * @return um {@link List} contendo todos os {@link Atestado} do paciente.
	 */
	public List<Atestado> buscarAtestadorPorCPF(String umCPF) {
		List<Atestado> meusAtestados = new ArrayList<>();
		/*try {
			PreparedStatement preparedStatement = super.getConexao().prepareStatement(SQL_CONSULTA_POR_CPF);
			/// -----
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
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
	}
	
	/**
	 * 
	 * @param umAtestado
	 */
	public void gravarAtestado(Atestado umAtestado) {
		try {
			PreparedStatement preparedStatement = super.getConexao().prepareStatement(SQL_INSERT);
			/// -----
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
