package br.com.SIGEC.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.PessoaDAO;
import br.com.SIGEC.control.ProntuarioDAO;
import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.model.Prontuario;
import br.com.SIGEC.model.Usuario;

@ManagedBean
@ViewScoped
public class MeusProntuariosMBean extends AbstractMBean {
	
private List<Prontuario> meusProntuarios = new ArrayList<>();
	
	private Pessoa pessoa;

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
		
		if(usuario!=null) {
			pessoa = PessoaDAO.buscarPessoaPeloLogin(usuario.getLogin());
		}
		
		this.meusProntuarios = ProntuarioDAO.buscarProntuarioPorCPFDOPaciente(pessoa.getCpf());
	}

	public List<Prontuario> getMeusProntuarios() {
		return this.meusProntuarios;
	}
	
}


