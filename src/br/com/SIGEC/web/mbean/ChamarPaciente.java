package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;

/*Lembrete: cada lista terá um nome esapecífico com pacientes específicos que participaram dessa lista dependendo de suas necessidades,
 * Salas que tratam do mesmo problema podem ter uma lista compartilhada? Os pacientes terão responsáveis(médicos) específicos?*/

//O que devo fazer:
//Médico disponibiliza sala --?--
//ListaDeEspera da sala tem status atualizado --C--
//Verifica próximo paciente da lista --C--
//Se existe paciente, chama ele e atualiza a lista e estado da sala --?C-- (Por onde chamo ele? Onde fica aquela tela?)
//Aviso ao médico e mando os dados do próximo paciente --?-- (Para onde mando? Só mando o nome? Cpf também ou algo a mais?)
//Se não existe paciente, aviso ao médico --C-- (Para onde mando?)


public class ChamarPaciente {
	//botão próximo acionado
	
	@SuppressWarnings("unused") //não usado em nada, por enquanto
	private String chamarProximo(Sala sala) {
		String proximoPaciente = sala.getLista().get(0).getNomeCompleto();
		if (proximoPaciente != null) {
			//Atualiza lista
			sala.getLista().remove(0); //remove o índice visto e (atualiza automaticamente? pela net, diz que sim!)
			//Atualiza status
			sala.setEstado("ocupado");
		} else {
			//Sem pacientes disponíveis \(*-*)/
			proximoPaciente = "Lista vazia";
		}
		
		return proximoPaciente;
		
	}
}
