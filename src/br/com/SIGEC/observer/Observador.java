package br.com.SIGEC.observer;

import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.web.mbean.Consulta;

/**
 * Esta interface define a estrutura de um observador para a��o de confirmar uma consulta
 * 
 * @author Pierre Brito e Alana Gisele
 *
 */
public interface Observador {
	/**
	 * A��o que ser� realizada assim que o observador for chamado
	 * 
	 */
	public void notificar(Paciente paciente, Consulta consulta);
}
