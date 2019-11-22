package br.com.SIGEC.web.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.AtestadoDAO;
import br.com.SIGEC.model.Atestado;

@ManagedBean
@ViewScoped
public class MeusAtestadosMBean extends AbstractMBean {
	
	private List<Atestado> meusAtestados = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		AtestadoDAO atestadoDAO = new AtestadoDAO();
		this.meusAtestados = atestadoDAO.buscarAtestadosPorCPF("705.960.664-31");
	}

	public List<Atestado> getMeusAtestados() {
		return this.meusAtestados;
	}
	
}
