package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;

import javax.faces.bean.ManagedBean;

import br.com.SIGEC.control.ChamarPacienteDAO;

// Botar o botão chamar próximo

@ManagedBean
public class ChamarPacienteMB extends AbstractMBean{
	//botão próximo acionado
	
	public void chamarPaciente(Fila fila) {
		ChamarPacienteDAO proximo = new ChamarPacienteDAO();
		proximo.recuperarLista(fila);
		proximo.chamarProximo(fila);
	}
	
	
	
}
