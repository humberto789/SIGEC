package br.com.SIGEC.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import br.com.SIGEC.model.Fila;
import br.com.SIGEC.web.mbean.ChamarPaciente;
import br.com.SIGEC.web.mbean.GuicheMB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class chamarPacienteTest {
	
	void adiciona(){
	GuicheMB.conexaoComBancoMySQL();
	System.out.println();
	}
	
	@Test
	void testComPaciente() {
		//Cenário
		Fila fila = new Fila();
		ChamarPaciente proximo = new ChamarPaciente();
		GuicheMB.conexaoComBancoMySQL();
		proximo.RecuperaLista(fila);
		
		//Teste de cenário
		Assertions.assertEquals("1", proximo.chamarProximo(fila));
	}
	
	@Test
	void testSemPaciente() {
		//Cenário
		
		
		//Teste de cenário
	}
}
