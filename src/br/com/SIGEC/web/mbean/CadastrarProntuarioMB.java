package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.control.ProntuarioDAO;
import br.com.SIGEC.model.Prontuario;
import br.com.SIGEC.model.Usuario;

@ManagedBean
@RequestScoped
public class CadastrarProntuarioMB extends AbstractMBean{
	
	private Prontuario prontuario;
	
	public CadastrarProntuarioMB() {
		prontuario = new Prontuario();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
		
		if(usuario.getTipoUsuario()=="medico") {
			prontuario.setMedico(MedicoDAO.buscarMedicoPeloLoginDoUsuario(usuario.getLogin()));
		}
	}
	
	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public void cadastrar() {
		if(MedicoDAO.buscarMedicoPeloCRM(prontuario.getMedico().getCrm())!=null) {
			if(PacienteDAO.buscarPacientePeloCPF(prontuario.getPaciente().getPessoa().getCpf())!=null) {
				if(ProntuarioDAO.cadastrarProntuario(prontuario)) {
					super.exibirMensagemInformativa("Prontuario cadastrado com sucesso");
				}else {
					super.exibirMensagemInformativa("Houve algum erro ao cadastrar o prontuario");
				}
			}else {
				super.exibirMensagemDeErro("O paciente com esse CPF não existe");
			}
		}else {
			super.exibirMensagemDeErro("O médico com esse CRM não existe");
		}
		
	}

	
}