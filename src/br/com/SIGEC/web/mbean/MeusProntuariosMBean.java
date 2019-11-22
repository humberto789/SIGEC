package br.com.SIGEC.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.ProntuarioDAO;
import br.com.SIGEC.model.Prontuario;

@ManagedBean
@ViewScoped
public class MeusProntuariosMBean extends AbstractMBean {
	
private List<Prontuario> meusProntuarios = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		ProntuarioDAO atestadoDAO = new ProntuarioDAO();
		this.meusProntuarios = ProntuarioDAO.buscarProntuarioPorCPFDOPaciente();
	}

	public List<Prontuario> getMeusProntuarios() {
		return this.meusProntuarios;
	}
	
}


