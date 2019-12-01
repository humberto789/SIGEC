package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.SIGEC.control.PacienteDAO;
import br.com.SIGEC.control.UsuarioDAO;
import br.com.SIGEC.control.Validador;
import br.com.SIGEC.model.Paciente;


@ManagedBean
@RequestScoped
public class CadastrarPacienteMB extends AbstractMBean{
	public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

	private Paciente paciente;

	public CadastrarPacienteMB() {
		this.paciente = new Paciente();
	}

	public void cadastrar() {
		if(Validador.validadorSenha(paciente.getPessoa().getUsuario().getSenha())) {
			if(Validador.validadorEmail(paciente.getPessoa().getUsuario().getEmail())) {
				if(UsuarioDAO.buscarUsuarioPorLoginESenha(paciente.getPessoa().getUsuario().getLogin(), paciente.getPessoa().getUsuario().getSenha())==null) {
					if (PacienteDAO.cadastrarPaciente(paciente)) {
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

	
}

