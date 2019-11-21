package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;



public class ChamarPaciente {
	//botão próximo acionado
	
	private final static String recuperarLista = "";
	
	@SuppressWarnings("unused") //não usado em nada, por enquanto
	private String chamarProximo(Fila fila) {
		String proximoPaciente = fila.getSenha();
		if (proximoPaciente != null) {
			//Atualiza lista
			fila.getLista().remove(0); //remove o índice visto e (atualiza automaticamente? pela net, diz que sim!)
		} else {
			//Sem pacientes disponíveis \(*-*)/
			proximoPaciente = "Lista vazia";
		}
		
		return proximoPaciente;
		
	}
}
