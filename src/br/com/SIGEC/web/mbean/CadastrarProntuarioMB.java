package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.*;
import br.com.SIGEC.control.ProntuarioDAO;

//Cadastrar um prontuario (em qual página?)
//formulário com peso, temperatura, altura, queixa, id (automatico pelo banco)
//jogar dados para o banco insert into



public class CadastrarProntuarioMB {
	
	
	private Prontuario prontuario;
	
	Prontuario pront = new Prontuario();
	ProntuarioDAO dao = new ProntuarioDAO();
	
	public void cadastro() {
		dao.cadastrarProntuario(pront);
	}

	
}