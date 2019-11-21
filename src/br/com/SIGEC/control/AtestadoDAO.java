package br.com.SIGEC.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	private static final String SQL_CONSULTA_POR_CPF = "SELECT atestado.cid, atestado.dataEmissao, atestado.dataVencimento, medico.crm, pessoaMedico.nome as medico, pessoaPaciente.nome as paciente FROM atestado INNER JOIN medico ON atestado.id_medico = medico.id INNER JOIN pessoa pessoaMedico ON medico.id_pessoa = pessoaMedico.id INNER JOIN paciente ON atestado.id_paciente = paciente.id INNER JOIN pessoa pessoaPaciente ON paciente.id_pessoa = pessoaPaciente.id WHERE pessoaPaciente.cpf = ?";
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
	
	public List<Atestado> buscarAtestadosPorCPF() {
		List<Atestado> meusAtestados = new ArrayList<>();
		try {
			PreparedStatement stmt = super.getConexao().prepareStatement(SQL_CONSULTA_POR_CPF);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Medico medico = new Medico();
				medico.setCrm(rs.getString("medico.crm"));
				medico.getPessoa().setNomeCompleto(rs.getString("pessoaMedico.nome"));
				
				Paciente paciente = new Paciente();
				paciente.getPessoa().setNomeCompleto(rs.getString("pessoaPaciente.nome"));
				
				Atestado atestado = new Atestado();
				atestado.setCid(rs.getString("atestado.cid"));
				atestado.setDataEmissao(new java.util.Date(rs.getDate("atestado.dataEmissao").getTime()));
				atestado.setDataVencimento(new java.util.Date(rs.getDate("atestado.dataVencimento").getTime()));
				atestado.setPaciente(paciente);
				atestado.setMedico(medico);
				
				meusAtestados.add(atestado);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return meusAtestados;
}

	
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