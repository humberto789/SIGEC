package br.com.SIGEC.web.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.control.PessoaDAO;
import br.com.SIGEC.model.Pessoa;

@ManagedBean
@ViewScoped // enquanto a página estiver aberta, o cpf terá uma "vida útil".
public class BloquearMB {

	private String cpf;
	private Pessoa pessoa;

	public BloquearMB() {
		pessoa = new Pessoa();
	}

	public void buscarCPF() {
		pessoa = PessoaDAO.buscarPessoaPorCpf(cpf);
	}

	public void bloqueia() {
		PessoaDAO.bloquearPorCPF(cpf);
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
