package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Sala;

//M�dico disponibiliza sala --?--
//listaDeEspera da sala tem status atualizado --C--
//verifica pr�ximo paciente da lista --C--
//se existe paciente, chama ele e atualiza a lista e estado da sala --?C--

public class ChamarPaciente {
	//bot�o pr�ximo acionado
	
	@SuppressWarnings("unused") //n�o usado em nada, por enquanto
	private String chamarProximo(Sala sala) {
		String proximoPaciente = sala.getLista().get(0).getNomeCompleto();
		if (proximoPaciente != null) {
			//Atualiza lista
			sala.getLista().remove(0); //remove o �ndice visto e (atualiza automaticamente? pela net, diz que sim!)
			//Atualiza status
			sala.setEstado("ocupado");
		} else {
			//Sem pacientes dispon�veis \(*-*)/
			proximoPaciente = "Lista vazia";
		}
		
		return proximoPaciente;
		
	}
}
