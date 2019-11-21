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
		//Cenário
		Fila fila = new Fila();
		ChamarPaciente proximo = new ChamarPaciente();
		
		//Teste de cenário
		Assertions.assertEquals("senhaEsperada", proximo.chamarProximo(fila));
	}
	
	@Test
	void testSemPaciente() {
		//Cenário
		
		
		//Teste de cenário
	}
}
