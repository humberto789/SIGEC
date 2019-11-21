package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;



public class ChamarPaciente {
	//bot�o pr�ximo acionado
	
	private final static String recuperarLista = "";
	
	@SuppressWarnings("unused") //n�o usado em nada, por enquanto
	private String chamarProximo(Fila fila) {
		String proximoPaciente = fila.getSenha();
		if (proximoPaciente != null) {
			//Atualiza lista
			fila.getLista().remove(0); //remove o �ndice visto e (atualiza automaticamente? pela net, diz que sim!)
		} else {
			//Sem pacientes dispon�veis \(*-*)/
			proximoPaciente = "Lista vazia";
		}
		
		return proximoPaciente;
		
	}
}
