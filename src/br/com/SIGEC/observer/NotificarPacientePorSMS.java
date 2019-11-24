package br.com.SIGEC.observer;

import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;

public class NotificarPacientePorSMS implements Observador {

	@Override
	public void agir(Medico medico, Paciente paciente) {
		System.out.println("Enviado SMS... " + paciente.getPessoa().getTelefone());

	}

}
