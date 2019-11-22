package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.model.Paciente;


@ManagedBean
@RequestScoped
public class CadastrarPacienteMB extends AbstractMBean{
	public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

	private Paciente paciente;

	public CadastrarPacienteMB() {
		this.paciente = new Paciente();
	}

	public void cadastrar() {
		if (PacienteDAO.cadastroPaciente(paciente)) {
			super.exibirMensagemInformativa("Cadastro feito com sucesso");
		}
		super.exibirMensagemDeErro("Deu algum erro, se vira");
	}

	
}

