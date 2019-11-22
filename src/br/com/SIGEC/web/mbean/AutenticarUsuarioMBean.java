package br.com.SIGEC.web.mbean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import br.com.SIGEC.control.UsuarioDAO;
import br.com.SIGEC.model.Usuario;

@ManagedBean
@SessionScoped
public class AutenticarUsuarioMBean extends AbstractMBean{
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void autenticar() {
		
		usuario = UsuarioDAO.buscarUsuarioPorLoginESenha(usuario.getLogin(), usuario.getSenha());
		
		if(true/*usuario != null*/) {
			try {
				
				HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				sessao.setAttribute("usuario_logado", usuario);
				//Teste
				FacesContext.getCurrentInstance().getExternalContext().redirect("home_admin.jsf");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			exibirMensagemDeErro("Autenticação falhou. Login e/ou senha não encontrados");
		}
		
	}
	
}
