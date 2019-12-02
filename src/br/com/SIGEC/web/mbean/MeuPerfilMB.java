package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.control.PessoaDAO;
import br.com.SIGEC.model.Consulta;
import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.model.Usuario;

@ManagedBean
@ViewScoped
public class MeuPerfilMB extends AbstractMBean {
	
	private Pessoa pessoa;

	public MeuPerfilMB() {
		pessoa = new Pessoa();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
		
		if(usuario!=null) {
			pessoa = PessoaDAO.buscarPessoaPeloLogin(usuario.getLogin());
		}
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
