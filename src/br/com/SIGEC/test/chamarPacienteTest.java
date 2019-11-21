package br.com.SIGEC.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import br.com.SIGEC.control.ChamarPacienteDAO;
import br.com.SIGEC.control.GuicheDAO;
import br.com.SIGEC.model.Fila;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class chamarPacienteTest {
	
	@Test
	void testComPaciente() {
		//Cenário
		Fila fila = new Fila();
		ChamarPacienteDAO proximo = new ChamarPacienteDAO();
		GuicheDAO.guicheSenha();
		proximo.recuperarLista(fila);
		
		//Teste de cenário
		Assertions.assertEquals("1", proximo.chamarProximo(fila));
	}
	
	@Test
	void testSemPaciente() {
		//Cenário
		
		
		//Teste de cenário
	}
}
