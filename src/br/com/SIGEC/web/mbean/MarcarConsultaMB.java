package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.ConsultaDAO;
import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.model.Consulta;
import br.com.SIGEC.model.Usuario;

@ManagedBean
@RequestScoped
public class MarcarConsultaMB extends AbstractMBean{
	private Consulta consulta;
	
	public MarcarConsultaMB() {
		consulta = new Consulta();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
		
		if(usuario.getTipoUsuario()=="paciente") {
			consulta.setPaciente(PacienteDAO.buscarPacientePeloLoginDoUsuario(usuario.getLogin()));
		}
		
	}
	
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public void marcar() {
		if(MedicoDAO.buscarMedicoPeloCRM(consulta.getMedico().getCrm())!=null) {
			if(PacienteDAO.buscarPacientePeloCPF(consulta.getPaciente().getPessoa().getCpf())!=null) {
				if(!ConsultaDAO.verificarSeConsultaExiste(consulta)) {
					if(ConsultaDAO.cadastrarConsulta(consulta)) {
						super.exibirMensagemInformativa("Consulta marcada");
					}else {
						super.exibirMensagemDeErro("Houve algum problema ao marcar a consulta");
					}
				}else {
					super.exibirMensagemDeErro("Horário indisponivel");
				}
			}else {
				super.exibirMensagemDeErro("O paciente com esse cpf não existe");
			}
		}else {
			super.exibirMensagemDeErro("O médico com esse CRM não existe");
		}
	}
	
}
