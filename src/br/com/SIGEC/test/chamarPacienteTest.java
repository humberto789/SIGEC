package br.com.SIGEC.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import br.com.SIGEC.control.FilaDAO;
import br.com.SIGEC.control.GuicheDAO;
import br.com.SIGEC.model.Fila;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class chamarPacienteTest {
	
	@Test
	void testComPaciente() {
		//Cen�rio
		Fila fila = new Fila();
		FilaDAO proximo = new FilaDAO();
		GuicheDAO.guicheSenha();
		proximo.recuperarLista(fila);
		
		//Teste de cen�rio
		Assertions.assertEquals("1", proximo.chamarProximo(fila));
	}
	
	@Test
	void testSemPaciente() {
		//Cen�rio
		
		
		//Teste de cen�rio
	}
}
