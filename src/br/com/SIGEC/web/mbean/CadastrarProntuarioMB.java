package br.com.SIGEC.web.mbean;

import br.com.SIGEC.model.*;

//Cadastrar um prontuario (em qual p�gina?)
//formul�rio com peso, temperatura, altura, queixa, id (automatico pelo banco)
//jogar dados para o banco insert into



public class CadastrarProntuarioMB {
	
	private final static String cadastrar = "insert into pessoa(peso, altura, id, alergia,queixa, temperatura) value('?','?','?','?','?','?');"; //C�digo insert into do banco
	
	private Prontuario prontuario;

	public CadastrarProntuarioMB() {
		this.prontuario = new Prontuario();
	}

	public void cadastraProntuario() {
		
}  
}