package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;

//Recupera lista e adiciona a lista daqui
//Deleta valor da lista de lá

public class ChamarPaciente {
	//botão próximo acionado
	
	private final static String recuperarLista = "select senha from fila";
	
	private final static String atualizarLista = "delete from fila where fila equals '?'"; // Senha atual
	
	@SuppressWarnings("unused") //não usado em nada, por enquanto
	private String chamarProximo(Fila fila) {
		String proximoPaciente = fila.getSenha();
		if (proximoPaciente != null) {
			//Atualiza lista (Conexão com o banco + String atualizar lista)
		} else {
			//Sem pacientes disponíveis \(*-*)/
			proximoPaciente = "Lista vazia";
		}
		
		return proximoPaciente;
		
	}
	
	
}
