package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.model.Medico;

@ManagedBean
@RequestScoped
public class CadastrarMedicoMB extends AbstractMBean {

	private Medico medico;

	public CadastrarMedicoMB() {
		this.medico = new Medico();
	}

	public void cadastrar() {
		if (MedicoDAO.cadastroMedico(medico)) {
			super.exibirMensagemInformativa("Cadastro feito com sucesso");
		}
		super.exibirMensagemDeErro("Deu algum erro, se vira");
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}