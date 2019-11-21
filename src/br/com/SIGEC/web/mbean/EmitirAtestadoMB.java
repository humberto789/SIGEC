package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.AtestadoDAO;
import br.com.SIGEC.model.Atestado;

@ManagedBean
@ViewScoped
public class EmitirAtestadoMB extends AbstractMBean{
	
	Atestado atestado = new Atestado();
	
	public void emitir() {
		
		if(AtestadoDAO.gravarAtestado(atestado)) {
			super.exibirMensagemInformativa("Atestado emitido com sucesso!");
		}
		super.exibirMensagemDeErro("Ops! Você cometeu um erro.");
		
	}
	
}
