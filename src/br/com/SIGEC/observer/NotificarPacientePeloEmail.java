package br.com.SIGEC.observer;

import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;

public class NotificarPacientePeloEmail implements Observador {

	@Override
	public void agir(Medico medico, Paciente paciente) {
		System.out.println("Notificando pelo e-mail..." + paciente.getPessoa().getEmail());		
	}

}
