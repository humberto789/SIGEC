package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.FilaDAO;

// Botar o botão chamar próximo

@ManagedBean
@RequestScoped
public class ChamarPacienteMB extends AbstractMBean{
	//botão próximo acionado
	
	public void chamarPaciente() {
		String senha = FilaDAO.chamarProximo();
		
		super.exibirMensagemInformativa("A senha do paciente é: " + senha);
	}
	
	
	
}
