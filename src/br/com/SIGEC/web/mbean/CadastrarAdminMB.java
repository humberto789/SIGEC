package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.AdminDAO;
import br.com.SIGEC.control.UsuarioDAO;
import br.com.SIGEC.control.Validador;
import br.com.SIGEC.model.Admin;

@RequestScoped
@ManagedBean
public class CadastrarAdminMB extends AbstractMBean{
	private Admin admin;

	public CadastrarAdminMB() {
		this.admin = new Admin();
	}

	public void cadastrar() {
		
		if(Validador.validadorSenha(admin.getPessoa().getUsuario().getSenha())) {
			if(Validador.validadorEmail(admin.getPessoa().getUsuario().getEmail())) {
				if(UsuarioDAO.buscarUsuarioPorLoginESenha(admin.getPessoa().getUsuario().getLogin(), admin.getPessoa().getUsuario().getSenha())==null) {
					if (AdminDAO.cadastrarAdmin(admin)) {
						super.exibirMensagemInformativa("Cadastro feito com sucesso");
					}else {
						super.exibirMensagemDeErro("Houve algum problema no cadastro");
					}
				}else {
					super.exibirMensagemDeErro("Login e/ou senha já existem");
				}
			}else {
				super.exibirMensagemDeErro("Email inválido");
			}
		}
		else {
			super.exibirMensagemDeErro("Senha inválida: Tem que ter no minimo tres tipos de caracteres");
		}
		
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
