package br.com.SIGEC.control;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.SIGEC.model.Pessoa;

@ManagedBean
@ViewScoped // enquanto a página estiver aberta, o cpf irá ter uma "vida útil".
public class BloquearBean {

	private String cpf;
	private Pessoa pessoa;

	public BloquearBean() {
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
