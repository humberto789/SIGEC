package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.ConsultaDAO;
import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.model.Medico;
import br.com.SIGEC.model.Usuario;

@ManagedBean
@ViewScoped
public class EditarConsultaBean {
	private br.com.SIGEC.model.Consulta consulta;
	private int id;
	
	public void carregarConsulta() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
		
		Medico medico = MedicoDAO.buscarMedicoPeloLoginDoUsuario(usuario.getLogin());
		this.consulta = ConsultaDAO.buscar(this.id, medico);	
	}
	
	public String cancelarConsulta() {
		ConsultaDAO.cancelarConsulta(this.consulta);
		return "agenda_medica?faces-redirect=true";
	}
	
	public String salvar() {
		ConsultaDAO.atualizar(this.consulta);
		return "agenda_medica?faces-redirect=true";
	}
	
	public br.com.SIGEC.model.Consulta getConsulta() {
		return consulta;
	}
	
	public void setConsulta(br.com.SIGEC.model.Consulta consulta) {
		this.consulta = consulta;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
