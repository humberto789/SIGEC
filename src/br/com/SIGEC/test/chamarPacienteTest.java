package br.com.SIGEC.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import br.com.SIGEC.model.Fila;
import br.com.SIGEC.web.mbean.ChamarPaciente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class chamarPacienteTest {
	
	@Test
	void testComPaciente() {
		//Cen�rio
		Fila fila = new Fila();
		ChamarPaciente proximo = new ChamarPaciente();
		
		//Teste de cen�rio
		Assertions.assertEquals("senhaEsperada", proximo.chamarProximo(fila));
	}
	
	@Test
	void testSemPaciente() {
		//Cen�rio
		
		
		//Teste de cen�rio
	}
}
