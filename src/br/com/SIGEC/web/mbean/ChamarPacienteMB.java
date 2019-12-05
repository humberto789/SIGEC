package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.Fila;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.FilaDAO;

// Botar o bot�o chamar pr�ximo

@ManagedBean
@RequestScoped
public class ChamarPacienteMB extends AbstractMBean{
	//bot�o pr�ximo acionado
	
	public void chamarPaciente() {
		String senha = FilaDAO.chamarProximo();
		
		super.exibirMensagemInformativa("A senha do paciente �: " + senha);
	}
	
	
	
}
