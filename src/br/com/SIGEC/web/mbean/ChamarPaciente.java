package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;

/*Lembrete: cada lista ter� um nome esapec�fico com pacientes espec�ficos que participaram dessa lista dependendo de suas necessidades,
 * Salas que tratam do mesmo problema podem ter uma lista compartilhada? Os pacientes ter�o respons�veis(m�dicos) espec�ficos?*/

//O que devo fazer:
//M�dico disponibiliza sala --?--
//ListaDeEspera da sala tem status atualizado --C--
//Verifica pr�ximo paciente da lista --C--
//Se existe paciente, chama ele e atualiza a lista e estado da sala --?C-- (Por onde chamo ele? Onde fica aquela tela?)
//Aviso ao m�dico e mando os dados do pr�ximo paciente --?-- (Para onde mando? S� mando o nome? Cpf tamb�m ou algo a mais?)
//Se n�o existe paciente, aviso ao m�dico --C-- (Para onde mando?)


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
