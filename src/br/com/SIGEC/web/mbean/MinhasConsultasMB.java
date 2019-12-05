package br.com.SIGEC.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.ConsultaDAO;
import br.com.SIGEC.control.PessoaDAO;
import br.com.SIGEC.model.Pessoa;
import br.com.SIGEC.model.Usuario;
import br.com.SIGEC.model.Consulta;

@ManagedBean
@ViewScoped
public class MinhasConsultasMB extends AbstractMBean{
		
	private Pessoa pessoa;
	
	private List<Consulta> minhasConsultas = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
		
		if(usuario!=null) {
			pessoa = PessoaDAO.buscarPessoaPeloLogin(usuario.getLogin());
		}
		
		this.minhasConsultas = ConsultaDAO.buscarConsultaPorCPF(pessoa.getCpf());
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Consulta> getMinhasConsultas() {
		return minhasConsultas;
	}

	public void setMinhasConsultas(List<Consulta> minhasConsultas) {
		this.minhasConsultas = minhasConsultas;
	}
}



