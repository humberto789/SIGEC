package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.RecepcionistaDAO;
import br.com.SIGEC.control.UsuarioDAO;
import br.com.SIGEC.control.Validador;
import br.com.SIGEC.model.Recepcionista;

@RequestScoped
@ManagedBean
public class CadastrarRecepcionistaMB extends AbstractMBean{
	
	private Recepcionista recepcionista;
	
	public CadastrarRecepcionistaMB() {
		this.recepcionista = new Recepcionista();
	}
	
	public void cadastrar() {
		if(Validador.validadorSenha(recepcionista.getPessoa().getUsuario().getSenha())) {
			if(Validador.validadorEmail(recepcionista.getPessoa().getUsuario().getEmail())) {
				if(UsuarioDAO.buscarUsuarioPorLoginESenha(recepcionista.getPessoa().getUsuario().getLogin(), recepcionista.getPessoa().getUsuario().getSenha())==null) {
					if (RecepcionistaDAO.cadastrarRecepcionista(recepcionista)) {
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

	public Recepcionista getRecepcionista() {
		return recepcionista;
	}
	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}
}
