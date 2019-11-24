package br.com.SIGEC.observer;

import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;

/**
 * Esta interface define a estrutura de um observador da a��o de atualizar a agenda
 * 
 * @author Pierre Brito
 *
 */
public interface Observador {
	/**
	 * 
	 * @param medico - M�dico  dono da agenda
	 * @param paciente - Paciente da consulta modificada
	 */
	public void agir(Medico medico, Paciente paciente);
}
