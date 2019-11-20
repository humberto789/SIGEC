package br.com.SIGEC.model;

import java.util.ArrayList;

public class Sala {
	private int numeroSala;
	private Medico responsavel;
	private ArrayList<Paciente> lista;
	private String estado;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}
	public Medico getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Medico responsavel) {
		this.responsavel = responsavel;
	}
	public ArrayList<Paciente> getLista() {
		return lista;
	}
	public void setLista(ArrayList<Paciente> lista) {
		this.lista = lista;
	}
	
}
