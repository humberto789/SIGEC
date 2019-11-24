package br.com.SIGEC.observer;

import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;

public class NotificarRecepcionista implements Observador {

	@Override
	public void agir(Medico medico, Paciente paciente) {
		//Recepcionista deve ligar para o paciente para informar a mudança
		System.out.println("Recepcionista, ligue para o paciente.");
	}

}
