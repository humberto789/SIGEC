package br.com.SIGEC.web.mbean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractMBean {

	/**
	 * Método utilizado para exibir uma <b>mensagem de erro</b> na página JSF.
	 * 
	 * @param umaMensagem
	 *            a mensagem que será exibida ao usuário.
	 */
	public void exibirMensagemDeErro(String umaMensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, umaMensagem, umaMensagem));
	}

	/**
	 * Método utilizado para exibir uma <b>mensagem informativa</b> na página JSF.
	 * 
	 * @param umaMensagem
	 *            a mensagem que será exibida ao usuário.
	 */
	public void exibirMensagemInformativa(String umaMensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, umaMensagem, umaMensagem));
	}

	/**
	 * Método utilizado para exibir uma<b> mensagem de alerta</b> na página JSF.
	 * 
	 * @param umaMensagem
	 *            a mensagem que será exibida ao usuário.
	 */
	public void exibirMensagemDeAlerta(String umaMensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, umaMensagem, umaMensagem));
	}

}
