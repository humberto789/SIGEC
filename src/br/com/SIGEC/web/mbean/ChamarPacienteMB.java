package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;

import javax.faces.bean.ManagedBean;

import br.com.SIGEC.control.ChamarPacienteDAO;

// Botar o bot�o chamar pr�ximo

@ManagedBean
public class ChamarPacienteMB extends AbstractMBean{
	//bot�o pr�ximo acionado
	
	public void chamarPaciente(Fila fila) {
		ChamarPacienteDAO proximo = new ChamarPacienteDAO();
		proximo.recuperarLista(fila);
		proximo.chamarProximo(fila);
	}
	
	
	
}
