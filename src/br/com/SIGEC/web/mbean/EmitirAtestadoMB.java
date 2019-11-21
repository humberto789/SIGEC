package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.AtestadoDAO;
import br.com.SIGEC.model.Atestado;

@ManagedBean
@ViewScoped
public class EmitirAtestadoMB extends AbstractMBean{
	
	private Atestado atestado = new Atestado();
	
	public void emitir() {
		System.out.println("Emitindo atestado");
		if(AtestadoDAO.gravarAtestado(atestado)) {
			super.exibirMensagemInformativa("Atestado emitido com sucesso!");
		}else{
			super.exibirMensagemDeErro("Ops! Você cometeu um erro.");
		}
		
	}

	public Atestado getAtestado() {
		return atestado;
	}

	public void setAtestado(Atestado atestado) {
		this.atestado = atestado;
	}
	
	
	
}
