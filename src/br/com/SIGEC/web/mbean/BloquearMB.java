package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.PessoaDAO;
import br.com.SIGEC.model.Pessoa;

@ManagedBean
@ViewScoped // enquanto a p�gina estiver aberta, o cpf ter� uma "vida �til".
public class BloquearMB extends AbstractMBean {

	private String cpf;
	private Pessoa pessoa;

	public BloquearMB() {
		//pessoa = new Pessoa();
	}
	
	public void buscarCPF() {
		pessoa = PessoaDAO.buscarPessoaPorCpf(cpf);
	}

	public void bloqueia() {
		PessoaDAO.bloquearPorCPF(cpf);
		super.exibirMensagemInformativa("Usu�rio bloqueado com sucesso!");
	}
	
	public void desbloqueia() {
		PessoaDAO.desbloquearPorCPF(cpf);
		super.exibirMensagemInformativa("Usu�rio desbloqueado com sucesso!");
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
