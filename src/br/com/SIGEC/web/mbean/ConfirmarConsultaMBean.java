package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.ConsultaDAO;
import br.com.SIGEC.control.FilaDAO;
import br.com.SIGEC.control.GeradorSenha;
import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.model.Consulta;
import br.com.SIGEC.model.Fila;

@ManagedBean
@RequestScoped
public class ConfirmarConsultaMBean extends AbstractMBean{
	
	private Consulta consulta;
	
	public ConfirmarConsultaMBean() {
		consulta = new Consulta();
	}
	
	public void confirmar() {
		if(MedicoDAO.buscarMedicoPeloCRM(consulta.getMedico().getCrm())!=null) {
			if(PacienteDAO.buscarPacientePeloCPF(consulta.getPaciente().getPessoa().getCpf())!=null) {
				if(ConsultaDAO.verificarSeConsultaExiste(consulta)) {
					if(ConsultaDAO.confirmarConsulta(consulta)) {
						String senha = GeradorSenha.gerarSenhaAleatoria();
						Fila fila = new Fila();
						fila.setSenha(senha);
						fila.setConsulta(consulta);
						
						FilaDAO.cadastrarSenha(fila);
						
						super.exibirMensagemInformativa("Consulta confirmada com sucesso. A senha do Paciente é: " + senha);
					}else {
						super.exibirMensagemDeErro("Ocorreu algum erro ao confirmar a consulta");
					}
				}else {
					super.exibirMensagemInformativa("Essa consulta não existe");
				}
			}else {
				super.exibirMensagemDeErro("O paciente com esse CPF não existe");
			}
		}else {
			super.exibirMensagemDeErro("O médico com esse CRM não existe");
		}
	}

	public Consulta getConsulta() {
		return consulta;
	}
	
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
}
