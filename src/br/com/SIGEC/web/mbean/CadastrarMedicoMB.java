package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.MedicoDAO;
import br.com.SIGEC.control.UsuarioDAO;
import br.com.SIGEC.control.Validador;
import br.com.SIGEC.model.Medico;

@ManagedBean
@RequestScoped
public class CadastrarMedicoMB extends AbstractMBean {

	private Medico medico;

	public CadastrarMedicoMB() {
		this.medico = new Medico();
	}

	public void cadastrar() {
		
		if(Validador.validadorSenha(medico.getPessoa().getUsuario().getSenha())) {
			if(Validador.validadorEmail(medico.getPessoa().getUsuario().getEmail())) {
				if(UsuarioDAO.buscarUsuarioPorLoginESenha(medico.getPessoa().getUsuario().getLogin(), medico.getPessoa().getUsuario().getSenha())==null) {
					if (MedicoDAO.cadastroMedico(medico)) {
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}