package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.FilaDAO;

@ManagedBean
@ViewScoped
public class GuicheMBean extends AbstractMBean{
	private String senha;
	
	public GuicheMBean(){
		senha = FilaDAO.recuperarUltimaSenha();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
