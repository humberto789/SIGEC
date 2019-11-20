package br.com.SIGEC.web.mbean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractMBean {

	/**
	 * M�todo utilizado para exibir uma <b>mensagem de erro</b> na p�gina JSF.
	 * 
	 * @param umaMensagem
	 *            a mensagem que ser� exibida ao usu�rio.
	 */
	public void exibirMensagemDeErro(String umaMensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, umaMensagem, umaMensagem));
	}

	/**
	 * M�todo utilizado para exibir uma <b>mensagem informativa</b> na p�gina JSF.
	 * 
	 * @param umaMensagem
	 *            a mensagem que ser� exibida ao usu�rio.
	 */
	public void exibirMensagemInformativa(String umaMensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, umaMensagem, umaMensagem));
	}

	/**
	 * M�todo utilizado para exibir uma<b> mensagem de alerta</b> na p�gina JSF.
	 * 
	 * @param umaMensagem
	 *            a mensagem que ser� exibida ao usu�rio.
	 */
	public void exibirMensagemDeAlerta(String umaMensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, umaMensagem, umaMensagem));
	}

}
