package br.com.SIGEC.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.AtestadoDAO;
import br.com.SIGEC.control.ProntuarioDAO;
import br.com.SIGEC.model.Atestado;
import br.com.SIGEC.model.Prontuario;

@ManagedBean
@ViewScoped
public class MeusProntuariosMBean extends AbstractMBean {
	
	private List<Prontuario> meusProntuarios = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		ProntuarioDAO prontarioDAO = new ProntuarioDAO();
		this.meusProntuarios = (List<Prontuario>) ProntuarioDAO.buscarProntuarioPorCPFDOPaciente("123.123.123-12");
	}

	public List<Prontuario> getMeusAtestados() {
		return this.meusProntuarios;
	}
	
}
