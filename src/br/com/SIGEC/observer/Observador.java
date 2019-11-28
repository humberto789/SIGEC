package br.com.SIGEC.observer;

import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.web.mbean.Consulta;

/**
 * Esta interface define a estrutura de um observador para ação de confirmar uma consulta
 * 
 * @author Pierre Brito e Alana Gisele
 *
 */
public interface Observador {
	/**
	 * Ação que será realizada assim que o observador for chamado
	 * 
	 */
	public void notificar(Paciente paciente, Consulta consulta);
}
