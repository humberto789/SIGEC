package br.com.SIGEC.observer;

import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.web.mbean.Consulta;

public class AtualizarNoBD implements Observador {
	
	@Override
	public void notificar(Paciente paciente, Consulta consulta) {
		//ConsultaDAO.confirmarConsulta(consulta);
	}

}
