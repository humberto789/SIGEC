package br.com.SIGEC.web.mbean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.AdminDAO;
import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.control.RecepcionistaDAO;
import br.com.SIGEC.control.UsuarioDAO;
import br.com.SIGEC.model.Admin;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Paciente;
import br.com.SIGEC.model.Recepcionista;
import br.com.SIGEC.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioLogadoMBean extends AbstractMBean{
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void autenticar() {
		
		usuario = UsuarioDAO.buscarUsuarioAtivoPorLoginESenha(usuario.getLogin(), usuario.getSenha());
		if(usuario != null) {
			try {
				usuario.setTipoUsuario(descobrindoTipoDeUsuario(usuario));
				
				FacesContext fc = FacesContext.getCurrentInstance();
		        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		        
		        if(session==null) {
		        	session = (HttpSession) fc.getExternalContext().getSession(true);
		        }
		        
		        session.setAttribute("usuario_logado", usuario);
				
				redirecionandoPaginaHomeUsuario(usuario.getTipoUsuario());
				
				
			}catch (IOException e) {
				usuario = new Usuario();
				e.printStackTrace();
			}
		}else {
			exibirMensagemDeErro("Autenticação falhou. Login e/ou senha não encontrados");
			usuario = new Usuario();
		}
		
	}

	private String descobrindoTipoDeUsuario(Usuario umUsuario) {
		Paciente paciente = PacienteDAO.buscarPacientePeloLoginDoUsuario(umUsuario.getLogin());
		Medico medico = MedicoDAO.buscarMedicoPeloLoginDoUsuario(umUsuario.getLogin());
		Admin admin = AdminDAO.buscarAdminPeloLoginDoUsuario(umUsuario.getLogin());
		Recepcionista recepcionista = RecepcionistaDAO.buscarRecepcionistaPeloLoginDoUsuario(umUsuario.getLogin());
		
		if(paciente != null) {
			return "paciente";
		}else if(medico != null) {
			return "medico";
		}else if(admin != null) {
			return "admin";
		}else if(recepcionista != null) {
			return "recepcionista";
		}else{
			return "deslogado";
		}
	}
	
	private void redirecionandoPaginaHomeUsuario(String umTipoDeUsuario) throws IOException {
		
		if(umTipoDeUsuario.equals("paciente")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paciente/home_paciente.jsf");
		}else if(umTipoDeUsuario.equals("medico")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("medico/home_medico.jsf");
		}else if(umTipoDeUsuario.equals("admin")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("admin/home_admin.jsf");
		}else if(umTipoDeUsuario.equals("recepcionista")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("recepcionista/home_recepcionista.jsf");
		}else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.jsf");
		}
		
	}
}
